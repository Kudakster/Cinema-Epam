package com.epam.cinema.service.implementation;

import com.epam.cinema.dao.implementation.DAOMovieImpl;
import com.epam.cinema.enity.Movie;
import com.epam.cinema.enity.Screening;
import com.epam.cinema.service.IMovieService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieServiceImpl implements IMovieService {
    private DAOMovieImpl daoMovie;
    private static MovieServiceImpl instance = null;

    public MovieServiceImpl() {
        daoMovie = new DAOMovieImpl();
    }

    @Override
    public List<Movie> findAllMovies() {
        return daoMovie.getAllMovies();
    }

    @Override
    public Movie findMovieById(Integer id) {
        return daoMovie.getMovieByID(id);
    }

    @Override
    public Movie findMovieByName(String name) {
        return daoMovie.getMovieByName(name);
    }

    @Override
    public Movie findMovieByTicketID(Integer id) {
        return daoMovie.getMovieByTicketID(id);
    }

    @Override
    public List<Movie> findMoviesByPagination(Integer missedPages, Integer numberPages) {
        return daoMovie.getMoviesByPagination(missedPages, numberPages);
    }

    @Override
    public List<Movie> findMoviesByNamePattern(String name) {
        return daoMovie.getMoviesByNamePattern(name);
    }

    @Override
    public int countAllMovies() {
        return daoMovie.countAllMovies();
    }

    @Override
    public boolean addMovie(Movie movie) {
        return daoMovie.addMovie(movie);
    }

    @Override
    public boolean updateMovie(Movie movie) {
        return daoMovie.updateMovieByID(movie);
    }

    @Override
    public boolean deleteMovieByID(Integer id) {
        return daoMovie.deleteMovieByID(id);
    }

    public Map<Integer, String> getMapMovieIdAndMovieName(List<Screening> screenings) {
        return screenings.stream()
                .map(Screening::getMovieID)
                .distinct()
                .map(this::findMovieById)
                .collect(Collectors.toMap(Movie::getId, Movie::getName));
    }

    public static MovieServiceImpl getInstance() {
        if (instance == null)
            instance = new MovieServiceImpl();
        return instance;
    }

    public static void setInstance(MovieServiceImpl instance) {
        MovieServiceImpl.instance = instance;
    }
}
