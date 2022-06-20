package com.epam.cinema.commands.update;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.enity.User;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandUpdateUser implements ICommand {
    private final String COMMAND = "user";
    private final String ERROR_COMMAND = "user";
    private final String ERROR_KEY = "user.alreadyExist";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String surName = request.getParameter("surname");
        String firstName = request.getParameter("firstname");

        User user = (User) request.getSession().getAttribute("user");

        user.setLogin(login);
        user.setSurName(surName);
        user.setFirstName(firstName);

        return new Redirect(ServiceFactory.getUserService().updateUser(user), COMMAND, ERROR_COMMAND, ERROR_KEY);
    }
}
