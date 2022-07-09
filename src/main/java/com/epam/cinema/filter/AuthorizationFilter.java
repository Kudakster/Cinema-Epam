package com.epam.cinema.filter;

import com.epam.cinema.enity.User;
import com.epam.cinema.enity.enumeration.UserRole;
import com.epam.cinema.servlets.ServiceServlet;
import org.apache.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


public class AuthorizationFilter extends HttpFilter {
    private static final Logger log = Logger.getLogger(ServiceServlet.class);

    private static final String MAIN = "main";
    private static final String USER = "user";
    private static final String CINEMA_URI = "/cinema/";
    private static final List<String> GUEST_REGEX = List.of("registration", "login", "sign-in", "sign-up");
    private static final List<String> USER_REGEX = List.of("user", "password", "update-user", "logout", "buy-ticket", "validateLogin");
    private static final List<String> ADMIN_REGEX = List.of("admin", "movies", "auditorium", "a-movie", "u-movie", "u-schedule", "add-movie", "update-auditorium", "update-seats", "update-movie", "update-screening", "delete-movie", "delete-screening", "validateMovieName");
    private static final List<String> COMMAND_REGEX = List.of("main", "screening");

    private HttpServletRequest req;
    private HttpServletResponse res;
    private FilterChain chain;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
        init(req, res, chain);

        String uri = req.getRequestURI().replace(CINEMA_URI, "");
        AtomicBoolean isFilterDo = new AtomicBoolean(false);
        Map<List<String>, Runnable> map = new HashMap<>();
        map.put(GUEST_REGEX, this::authenticGuest);
        map.put(USER_REGEX, this::authenticUser);
        map.put(ADMIN_REGEX, this::authenticAdmin);
        map.put(COMMAND_REGEX, this::doFilterWithHandlingException);

        map.forEach((regex, method) -> {
            if (regex.contains(uri)) {
                isFilterDo.set(true);
                method.run();
            }
        });

        if (!isFilterDo.get()) {
            doFilterWithHandlingException();
        }
    }

    private void init(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
        this.req = req;
        this.res = res;
        this.chain = chain;
    }

    private void authenticGuest() {
        if (req.getSession().getAttribute(USER) == null) {
            doFilterWithHandlingException();
        } else {
            redirectToMainPage();
        }
    }

    private void authenticUser() {
        authentic(UserRole.USER, UserRole.ADMIN);
    }

    private void authenticAdmin() {
        authentic(UserRole.ADMIN);
    }

    private void authentic(UserRole... required) {
        UserRole userRole = getUserRole();
        if (userRole != null && List.of(required).contains(userRole)) {
            doFilterWithHandlingException();
        } else {
            redirectToMainPage();
        }
    }

    private UserRole getUserRole() {
        User user = (User) req.getSession().getAttribute(USER);
        return user != null ? user.getUserRole() : null;
    }

    private void redirectToMainPage() {
        try {
            res.sendRedirect(MAIN);
        } catch (IOException e) {
            log.error(e);
        }
    }

    private void doFilterWithHandlingException() {
        try {
            chain.doFilter(req, res);
        } catch (IOException | ServletException e) {
            log.error(e);
        }
    }
}
