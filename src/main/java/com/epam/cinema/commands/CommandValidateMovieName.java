package com.epam.cinema.commands;

import com.epam.cinema.enity.Movie;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CommandValidateMovieName implements ICommand {
    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        String movieName = request.getParameter("movie-name");

        List<Movie> movieList = ServiceFactory.getMovieService().findMoviesByNamePattern(movieName);
        if (movieList != null) {
            String name = movieList.stream()
                    .map(Movie::getName)
                    .collect(Collectors.joining(";"));
            try {
                response.getWriter().print(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return (req, resp) -> {};
    }
}
