package com.epam.cinema.service;

import com.epam.cinema.dao.implementation.DAOMovieImpl;
import com.epam.cinema.enity.Movie;
import com.epam.cinema.service.implementation.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private DAOMovieImpl daoMovie;

    @InjectMocks
    private MovieServiceImpl movieService;

    private Movie movie;

    @BeforeEach
    public void setup() {
        movie = new Movie();
        movie.setId(1);
        movie.setName("Java");
    }

    @DisplayName("JUnit test for findMovie method")
    @Test
    public void givenMovieObject_whenFindMovieByID_thenReturnMovieObject() {
        given(daoMovie.getMovieByID(movie.getId()))
                .willReturn(movie);

        Movie findMovie = movieService.findMovieById(movie.getId());
        assertThat(findMovie).isNotNull();
        assertThat(findMovie).isInstanceOf(Movie.class);
    }

    @DisplayName("JUnit test for findMovie method")
    @Test
    public void givenMovieObject_whenFindMovieByName_thenReturnMovieObject() {
        given(daoMovie.getMovieByName(movie.getName()))
                .willReturn(movie);

        Movie findMovie = movieService.findMovieByName(movie.getName());
        assertThat(findMovie).isNotNull();
        assertThat(findMovie).isInstanceOf(Movie.class);
    }

    @DisplayName("JUnit test for findAllMovie method")
    @Test
    public void givenMovieObject_whenFindAllMovie_thenReturnListOfMovieObject() {
        Movie anotherMovie = new Movie();
        anotherMovie.setId(2);
        anotherMovie.setName("Kotlin");

        given(daoMovie.getAllMovies()).willReturn(List.of(movie, anotherMovie));

        List<Movie> movies = movieService.findAllMovies();
        assertThat(movies).isNotNull();
        assertThat(movies.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllMovie method")
    @Test
    public void givenMovieObject_whenFindAllMovieByPagination_thenReturnListOfMovieObject() {
        Movie anotherMovie = new Movie();
        anotherMovie.setId(2);
        anotherMovie.setName("Kotlin");

        given(daoMovie.getMoviesByPagination(0, 2)).willReturn(List.of(movie, anotherMovie));

        List<Movie> movies = movieService.findMoviesByPagination(0, 2);
        assertThat(movies).isNotNull();
        assertThat(movies.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for saveMovie method")
    @Test
    public void givenMovieObject_whenAddMovie_thenReturnMovieObject() {
        given(daoMovie.addMovie(movie)).willReturn(true);

        boolean result = movieService.addMovie(movie);
        assertThat(result).isNotNull();
    }

    @DisplayName("JUnit test for updateMovie method")
    @Test
    public void givenMovieObject_whenUpdateMovie_thenReturnUpdateMovieObject() {
        given(daoMovie.updateMovieByID(movie)).willReturn(true);

        boolean result = movieService.updateMovie(movie);
        assertThat(result).isTrue();
    }

    @DisplayName("JUnit test for countAllMovie method")
    @Test
    public void givenMovieObject_whenCountAllMovie_thenReturnLongObject() {
        given(daoMovie.countAllMovies()).willReturn(2);

        int countOfMovies = movieService.countAllMovies();
        assertThat(countOfMovies).isNotNull();
        assertThat(countOfMovies).isEqualTo(2);
    }

    @DisplayName("JUnit test for deleteMovie method")
    @Test
    public void givenMovieObject_whenDeleteMovie_thenVoid() {
        given(daoMovie.deleteMovieByID(movie.getId())).willReturn(true);
        boolean result = movieService.deleteMovieByID(movie.getId());
        assertThat(result).isTrue();
    }
}
