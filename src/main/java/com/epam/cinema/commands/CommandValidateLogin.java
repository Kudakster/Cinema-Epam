package com.epam.cinema.commands;

import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandValidateLogin implements ICommand {
    private final String COMMAND = "user";
    private final String ERROR_COMMAND = "user";
    private final String ERROR_KEY = "user.loginTaken";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");

        return new Redirect(ServiceFactory.getUserService().findUserByLogin(login) != null, COMMAND, ERROR_COMMAND, ERROR_KEY);
    }
}
