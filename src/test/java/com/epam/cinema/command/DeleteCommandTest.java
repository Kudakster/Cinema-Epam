package com.epam.cinema.command;

import com.epam.cinema.commands.delete.CommandDeleteMovie;
import com.epam.cinema.commands.delete.CommandDeleteScreening;
import com.epam.cinema.service.implementation.MovieServiceImpl;
import com.epam.cinema.service.implementation.ScreeningServiceImpl;
import com.epam.cinema.servlets.Redirect;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;

@ExtendWith(MockitoExtension.class)
public class DeleteCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private CommandDeleteMovie commandDeleteMovie;
    @InjectMocks
    private CommandDeleteScreening commandDeleteScreening;

    @Test
    public void givenDeleteMovieCommand_whenMovieCanBeDeleting_thenReturnRedirect() {
        Integer movieId = 1;
        MovieServiceImpl instance = mock(MovieServiceImpl.class);
        MovieServiceImpl.setInstance(instance);

        when(request.getParameter("d-movie-id")).thenReturn(String.valueOf(movieId));
        when(instance.deleteMovieByID(movieId)).thenReturn(true);

        Redirect redirect = (Redirect) commandDeleteMovie.execute(request, response);
        verify(request, atLeast(1)).getParameter("d-movie-id");
        assertThat(redirect.isValid()).isTrue();
        assertThat(redirect.getCommand()).isEqualTo("admin");
    }

    @Test
    public void givenDeleteMovieCommand_whenMovieCanNotBeDeleting_thenReturnRedirect() {
        Integer movieId = 1;
        MovieServiceImpl instance = mock(MovieServiceImpl.class);
        MovieServiceImpl.setInstance(instance);

        when(request.getParameter("d-movie-id")).thenReturn(String.valueOf(movieId));
        when(instance.deleteMovieByID(movieId)).thenReturn(false);

        Redirect redirect = (Redirect) commandDeleteMovie.execute(request, response);
        verify(request, atLeast(1)).getParameter("d-movie-id");
        assertThat(redirect.isValid()).isFalse();
        assertThat(redirect.getCommand()).isEqualTo("admin");
        assertThat(redirect.getErrorKey()).isEqualTo("movie.delete.error");
    }

    @Test
    public void givenDeleteScreeningCommand_whenScreeningCanBeDeleting_thenReturnRedirect() {
        Integer screeningId = 1;
        ScreeningServiceImpl instance = mock(ScreeningServiceImpl.class);
        ScreeningServiceImpl.setInstance(instance);

        when(request.getParameter("screening-id")).thenReturn(String.valueOf(screeningId));
        when(instance.deleteScreeningByID(screeningId)).thenReturn(true);

        Redirect redirect = (Redirect) commandDeleteScreening.execute(request, response);
        verify(request, atLeast(1)).getParameter("screening-id");
        assertThat(redirect.isValid()).isTrue();
        assertThat(redirect.getCommand()).isEqualTo("admin");
    }

    @Test
    public void givenDeleteScreeningCommand_whenScreeningCanNotBeDeleting_thenReturnRedirect() {
        Integer screeningId = 1;
        ScreeningServiceImpl instance = mock(ScreeningServiceImpl.class);
        ScreeningServiceImpl.setInstance(instance);

        when(request.getParameter("screening-id")).thenReturn(String.valueOf(screeningId));
        when(instance.deleteScreeningByID(screeningId)).thenReturn(false);

        Redirect redirect = (Redirect) commandDeleteScreening.execute(request, response);
        verify(request, atLeast(1)).getParameter("screening-id");
        assertThat(redirect.isValid()).isFalse();
        assertThat(redirect.getCommand()).isEqualTo("admin");
        assertThat(redirect.getErrorKey()).isEqualTo("screening.delete.error");
    }
}
