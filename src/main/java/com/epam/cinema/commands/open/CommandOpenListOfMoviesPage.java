package com.epam.cinema.commands.open;

import com.epam.cinema.commands.CookieUtil;
import com.epam.cinema.commands.ICommand;
import com.epam.cinema.enity.Movie;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Forward;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class CommandOpenListOfMoviesPage implements ICommand {
    private final String PAGE = "movies";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        Integer missedPages = CookieUtil.readCookie("missedMovies", request);
        Integer numberPages = CookieUtil.readCookie("numberMovies", request);

        List<Movie> movies = ServiceFactory.getMovieService().findMoviesByPagination(missedPages, numberPages);
        String numberAllMovies = String.valueOf(ServiceFactory.getMovieService().countAllMovies());
        request.setAttribute("movieList", movies);

        response.addCookie(new Cookie("numberAllMovies", numberAllMovies));
        return new Forward(PAGE);
    }
}
