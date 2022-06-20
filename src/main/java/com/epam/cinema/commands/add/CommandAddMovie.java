package com.epam.cinema.commands.add;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.commands.RequestUtil;
import com.epam.cinema.enity.Movie;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandAddMovie implements ICommand {
    private final String COMMAND = "admin";
    private final String ERROR_COMMAND = "add-movie";
    private final String ERROR_KEY = "movie.alreadyExist";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        Movie movie = RequestUtil.getMovieFromRequest(request);

        return new Redirect(ServiceFactory.getMovieService().addMovie(movie), COMMAND, ERROR_COMMAND, ERROR_KEY);
    }
}
