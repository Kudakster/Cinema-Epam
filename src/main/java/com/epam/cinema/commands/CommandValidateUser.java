package com.epam.cinema.commands;

import com.epam.cinema.enity.User;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Redirect;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Objects;

public class CommandValidateUser implements ICommand {
    private final String COMMAND = "user";
    private final String ERROR_COMMAND = "login";
    private final String ERROR_KEY = "user.notFound";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = new User(login, password);
        String error = Validation.validate(user);

        if (!Objects.equals(error, "")) {
            return new Redirect(ERROR_COMMAND, error);
        }

        User userFromDB = ServiceFactory.getUserService().verifyUserAndReturnUser(user);

        if (userFromDB == null) {
            return new Redirect(ERROR_COMMAND, ERROR_KEY);
        }
//        request.getSession().setAttribute("date", new Date(System.currentTimeMillis()));
        request.getSession().setAttribute("user", userFromDB);
        return new Redirect(COMMAND);
    }
}
