package com.epam.cinema.commands.open;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.enity.Movie;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandOpenUpdateMoviePage implements ICommand {
    private final String PAGE = "updateMovie";
    private final String ERROR_PAGE = "admin";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("u-movie-id"));
        Movie movie = ServiceFactory.getMovieService().findMovieById(id);
        request.setAttribute("movie", movie);
        return new Forward(movie != null, PAGE, ERROR_PAGE);
    }
}
