package com.epam.cinema.commands;

import com.epam.cinema.enity.User;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class CommandValidateUser implements ICommand {
    private final String COMMAND = "user";
    private final String ERROR_COMMAND = "login";
    private final String ERROR_KEY = "user.notFound";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = new User(login, password);

//        Cookie error = Validation.validate(user);
//
//        if (!Objects.equals(error.getValue(), "")) {
//            response.addCookie(error);
//            return new Redirect(ERROR_COMMAND);
//        }

        User userFromDB = ServiceFactory.getUserService().verifyUser(user);
        boolean result = userFromDB != null;

        request.getSession().setAttribute("date", new Date(System.currentTimeMillis()));
        request.getSession().setAttribute("user", userFromDB);
        return new Redirect(result, COMMAND, ERROR_COMMAND, ERROR_KEY);
    }
}
