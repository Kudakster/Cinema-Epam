package com.epam.cinema.commands.update;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.commands.RequestUtil;
import com.epam.cinema.enity.Movie;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandUpdateMovie implements ICommand {
    private final String COMMAND = "admin";
    private final String ERROR_COMMAND = "admin";
    private final String ERROR_KEY = "movie.update.error";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        Movie movie = RequestUtil.getMovieFromRequest(request);
        Integer id = Integer.valueOf(request.getParameter("id"));
        movie.setId(id);
        return new Redirect(ServiceFactory.getMovieService().updateMovie(movie), COMMAND, ERROR_COMMAND, ERROR_KEY);
    }
}
