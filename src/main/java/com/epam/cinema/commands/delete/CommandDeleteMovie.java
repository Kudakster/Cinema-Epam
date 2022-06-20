package com.epam.cinema.commands.delete;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandDeleteMovie implements ICommand {
    private final String COMMAND = "admin";
    private final String ERROR_COMMAND = "admin";
    private final String ERROR_KEY = "movie.delete.error";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("d-movie-id"));
        return new Redirect(ServiceFactory.getMovieService().deleteMovieByID(id), COMMAND, ERROR_COMMAND, ERROR_KEY);
    }
}
