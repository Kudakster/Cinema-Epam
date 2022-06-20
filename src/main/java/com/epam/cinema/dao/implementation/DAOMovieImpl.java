package com.epam.cinema.dao.implementation;

import com.epam.cinema.dao.DAOGeneral;
import com.epam.cinema.dao.IDAOMovie;
import com.epam.cinema.dao.mysql.MySQLConstants;
import com.epam.cinema.dao.utils.Mapper;
import com.epam.cinema.enity.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DAOMovieImpl extends DAOGeneral<Movie> implements IDAOMovie<Movie> {

    public DAOMovieImpl() {
        super(Movie::new);
        Mapper<ResultSet, Movie> movieMapper = (ResultSet resultSet, Movie movie) -> {
            movie.setId(resultSet.getInt(1));
            movie.setName(resultSet.getString(2));
            movie.setActors(resultSet.getString(3));
            movie.setDirection(resultSet.getString(4));
            movie.setGenre(resultSet.getString(5));
            movie.setCountry(resultSet.getString(6));
            movie.setTrailerURL(resultSet.getString(7));
            movie.setImgURL(resultSet.getString(8));
            movie.setReleaseDate(resultSet.getDate(9));
            movie.setDurationMin(resultSet.getString(10));
            movie.setDescription(resultSet.getString(11));
        };
        super.setObjectMapper(movieMapper);

        Mapper<Movie, PreparedStatement> statementMapper = (Movie movie, PreparedStatement preparedStatement) -> {
            preparedStatement.setString(1, movie.getName());
            preparedStatement.setString(2, movie.getActors());
            preparedStatement.setString(3, movie.getDirection());
            preparedStatement.setString(4, movie.getGenre());
            preparedStatement.setString(5, movie.getCountry());
            preparedStatement.setString(6, movie.getTrailerURL());
            preparedStatement.setString(7, movie.getImgURL());
            preparedStatement.setDate(8, movie.getReleaseDate());
            preparedStatement.setString(9, movie.getDurationMin());
            preparedStatement.setString(10, movie.getDescription());
        };
        super.setStatementMapper(statementMapper);
    }

    @Override
    public Movie getMovieByID(Integer id) {
        return get(MySQLConstants.Movie.SQL_GET_MOVIE_BY_ID, id);
    }

    @Override
    public Movie getMovieByName(String name) {
        return get(MySQLConstants.Movie.SQL_GET_MOVIE_BY_NAME, name);
    }

    @Override
    public Movie getMovieByTicketID(Integer id) {
        return get(MySQLConstants.Movie.SQL_GET_MOVIE_BY_TICKET_ID, id);
    }

    @Override
    public List<Movie> getMoviesByNamePattern(String name) {
        return getAllBy(MySQLConstants.Movie.SQL_GET_MOVIE_BY_NAME_PATTERN, "%" + name + "%");
    }

    @Override
    public List<Movie> getAllMovies() {
        return getAll(MySQLConstants.Movie.SQL_GET_ALL_MOVIES);
    }

    @Override
    public List<Movie> getMoviesByPagination(Integer missedPages, Integer numberPages) {
        return getAllBy(MySQLConstants.Movie.SQL_GET_MOVIES_BY_PAGINATION, missedPages, numberPages);
    }

    @Override
    public boolean addMovie(Movie movie) {
        return add(movie, MySQLConstants.Movie.SQL_ADD_MOVIE);
    }

    @Override
    public boolean updateMovieByID(Movie movie) {
        return update(movie, MySQLConstants.Movie.SQL_UPDATE_MOVIE_BY_ID, 11, movie.getId());
    }

    @Override
    public boolean deleteMovieByID(Integer id) {
        return delete(id, MySQLConstants.Movie.SQL_DELETE_MOVIE_BY_ID);
    }

    @Override
    public int countAllMovies() {
        return count(MySQLConstants.Movie.SQL_COUNT_ALL_MOVIES);
    }
}
