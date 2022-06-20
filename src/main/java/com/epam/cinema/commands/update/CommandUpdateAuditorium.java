package com.epam.cinema.commands.update;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandUpdateAuditorium implements ICommand {
    private final String COMMAND = "admin";
    private final String ERROR_COMMAND = "admin";
    private final String ERROR_KEY = "auditorium.error";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        return new Redirect(ServiceFactory.getAuditoriumService().updateAuditorium(request), COMMAND, ERROR_COMMAND, ERROR_KEY);
    }
}
