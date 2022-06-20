package com.epam.cinema.dao;

import com.epam.cinema.enity.Movie;

import java.util.List;

public interface IDAOMovie<Movie> {
    Movie getMovieByID(Integer id);

    Movie getMovieByName(String name);

    Movie getMovieByTicketID(Integer id);

    List<Movie> getMoviesByNamePattern(String name);

    List<Movie> getAllMovies();

    List<Movie> getMoviesByPagination(Integer missedPages, Integer numberPages);

    boolean addMovie(Movie t);

    boolean updateMovieByID(Movie t);

    boolean deleteMovieByID(Integer id);

    int countAllMovies();
}
