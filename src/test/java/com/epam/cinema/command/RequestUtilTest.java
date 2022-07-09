package com.epam.cinema.command;

import com.epam.cinema.commands.RequestUtil;
import com.epam.cinema.enity.Movie;
import com.epam.cinema.enity.Screening;
import com.epam.cinema.enity.User;
import com.epam.cinema.service.implementation.MovieServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RequestUtilTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private Part part;

    @InjectMocks
    private RequestUtil requestUtil;

    @Test
    public void givenGetMovieFromRequest_whenMovieIsEmpty_thenReturnMovie() throws ServletException, IOException {
        when(request.getPart("image")).thenReturn(part);
        Movie movie = requestUtil.getMovieFromRequest(request);
        assertThat(movie).isNotNull();
    }

    @Test
    public void givenGetUserFromRequest_whenUserIsEmpty_thenReturnUser() {
        User user = requestUtil.getUserFromRequest(request);
        assertThat(user).isNotNull();
    }

    @Test
    public void givenGetScreeningFromRequest_whenScreeningIsEmpty_thenReturnScreening() {
        MovieServiceImpl movieService = mock(MovieServiceImpl.class);
        MovieServiceImpl.setInstance(movieService);

        when(movieService.findMovieByName(any())).thenReturn(new Movie());

        Screening screening = requestUtil.getScreeningFromRequest(request);
        assertThat(screening).isNotNull();
    }
}
