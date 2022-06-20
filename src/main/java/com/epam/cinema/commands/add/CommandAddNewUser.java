package com.epam.cinema.commands.add;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.commands.Validation;
import com.epam.cinema.enity.User;
import com.epam.cinema.enity.enumeration.UserRole;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Redirect;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class CommandAddNewUser implements ICommand {
    private final String COMMAND = "sign-in";
    private final String ERROR_COMMAND = "sign-up";
    private final String ERROR_KEY = "user.alreadyExist";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstname");
        String surName = request.getParameter("surname");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone-number");

        User user = new User(login, password, firstName, surName, email, phoneNumber, UserRole.USER);

        Cookie error = Validation.validate(user);

        if (!Objects.equals(error.getValue(), "")) {
            response.addCookie(error);
            return new Redirect(ERROR_COMMAND);
        }

        return new Redirect(ServiceFactory.getUserService().addUser(user), COMMAND, ERROR_COMMAND, ERROR_KEY);
    }
}
