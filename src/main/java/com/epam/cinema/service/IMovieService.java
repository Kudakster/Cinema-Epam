package com.epam.cinema.service;

import com.epam.cinema.enity.Movie;

import java.util.List;

public interface IMovieService {

    Movie findMovieById(Integer id);

    Movie findMovieByName(String name);

    Movie findMovieByTicketID(Integer id);

    List<Movie> findMoviesByNamePattern(String name);

    List<Movie> findMoviesByPagination(Integer start, Integer end);

    List<Movie> findAllMovies();

    int countAllMovies();

    boolean addMovie(Movie movie);

    boolean updateMovie(Movie movie);

    boolean deleteMovieByID(Integer id);
}
