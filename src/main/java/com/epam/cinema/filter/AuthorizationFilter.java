package com.epam.cinema.filter;

import com.epam.cinema.enity.User;
import com.epam.cinema.enity.enumeration.UserRole;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


public class AuthorizationFilter extends HttpFilter {
    private static final String MAIN = "main";
    private static final String USER = "user";
    private static final String CINEMA_URI = "/cinema/";
    private static final String GUEST_REGEX = "registration,login,sign-in,sign-up";
    private static final String USER_REGEX = "user,password,update-user,logout,buy-ticket,validateLogin";
    private static final String ADMIN_REGEX = "admin,movies,auditorium,a-movie,u-movie,u-schedule,add-movie," +
            "update-auditorium,update-seats,update-movie,update-screening,delete-movie,delete-screening,validateMovieName";
    private static final String COMMAND_REGEX = "main,screening";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
        String uri = req.getRequestURI().replace(CINEMA_URI, "");
        AtomicBoolean isFilterDo = new AtomicBoolean(false);
        Map<String, Runnable> map = new HashMap<>();
        map.put(GUEST_REGEX, () -> authenticGuest(req, res, chain));
        map.put(USER_REGEX, () -> authenticUser(req, res, chain));
        map.put(ADMIN_REGEX, () -> authenticAdmin(req, res, chain));
        map.put(COMMAND_REGEX, () -> doFilterWithHandlingException(req, res, chain));

        map.forEach((regex, method) -> {
            if (Arrays.asList(regex.split(",")).contains(uri)) {
                isFilterDo.set(true);
                method.run();
            }
        });

        if (!isFilterDo.get()) {
            doFilterWithHandlingException(req, res, chain);
        }
    }

    private static void authenticGuest(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
        if (req.getSession().getAttribute(USER) == null) {
            doFilterWithHandlingException(req, res, chain);
        } else {
            redirectToMainPage(res);
        }
    }

    private static void authenticUser(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
        if (req.getSession().getAttribute(USER) != null) {
            doFilterWithHandlingException(req, res, chain);
        } else {
            redirectToMainPage(res);
        }
    }

    private static void authenticAdmin(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
        User user = (User) req.getSession().getAttribute(USER);
        UserRole userRole;
        if (user != null) {
            userRole = user.getUserRole();
            if (userRole.equals(UserRole.ADMIN)) {
                doFilterWithHandlingException(req, res, chain);
            }
        } else {
            redirectToMainPage(res);
        }
    }

    private static void redirectToMainPage(HttpServletResponse res) {
        try {
            res.sendRedirect(MAIN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doFilterWithHandlingException(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
        try {
            chain.doFilter(req, res);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
