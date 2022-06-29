package com.epam.cinema.command;

import com.epam.cinema.commands.RequestUtil;
import com.epam.cinema.commands.add.CommandAddMovie;
import com.epam.cinema.commands.add.CommandAddNewTicket;
import com.epam.cinema.commands.add.CommandAddNewUser;
import com.epam.cinema.commands.add.CommandAddScreening;
import com.epam.cinema.enity.Movie;
import com.epam.cinema.enity.Screening;
import com.epam.cinema.enity.User;
import com.epam.cinema.service.implementation.MovieServiceImpl;
import com.epam.cinema.service.implementation.ScreeningServiceImpl;
import com.epam.cinema.service.implementation.UserServiceImpl;
import com.epam.cinema.servlets.Redirect;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Time;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;

@ExtendWith(MockitoExtension.class)
public class AddCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestUtil requestUtil;

    @InjectMocks
    private CommandAddMovie commandAddMovie;
    @InjectMocks
    private CommandAddNewTicket commandAddNewTicket;
    @InjectMocks
    private CommandAddNewUser commandAddNewUser;
    @InjectMocks
    private CommandAddScreening commandAddScreening;

    @Test
    public void givenAddMovieCommand_whenMovieCanBeAdding_thenReturnRedirect() {
        MovieServiceImpl instance = mock(MovieServiceImpl.class);
        MovieServiceImpl.setInstance(instance);
        Movie movie = new Movie();
        when(requestUtil.getMovieFromRequest(request)).thenReturn(movie);
        when(instance.addMovie(movie)).thenReturn(true);

        Redirect redirect = (Redirect) commandAddMovie.execute(request, response);
        assertThat(redirect.isValid()).isTrue();
        assertThat(redirect.getCommand()).isEqualTo("admin");
    }

    @Test
    public void givenAddUserCommand_whenUserCanBeAdding_thenReturnRedirect() {
        User user = new User();

        UserServiceImpl instance = mock(UserServiceImpl.class);
        UserServiceImpl.setInstance(instance);

        when(requestUtil.getUserFromRequest(request)).thenReturn(user);
        when(instance.addUser(user)).thenReturn(true);

        Redirect redirect = (Redirect) commandAddNewUser.execute(request, response);
        assertThat(redirect.isValid()).isTrue();
        assertThat(redirect.getCommand()).isEqualTo("login");
    }

    @Test
    public void givenAddUserCommand_whenUserWithInvalidData_thenReturnRedirect() {
        User user = new User();
        user.setLogin("Do");

        UserServiceImpl instance = mock(UserServiceImpl.class);
        UserServiceImpl.setInstance(instance);

        when(requestUtil.getUserFromRequest(request)).thenReturn(user);

        Redirect redirect = (Redirect) commandAddNewUser.execute(request, response);
        assertThat(redirect.isValid()).isFalse();
        assertThat(redirect.getCommand()).isEqualTo("sign-up");
        assertThat(redirect.getErrorKey()).isNotEmpty();
    }

    @Test
    public void givenAddScreeningCommand_whenScreeningDataIsValid_thenReturnRedirect() {
        long milsInOneHour = 3_600_000;

        Movie movie = new Movie();
        movie.setName("Name");
        movie.setDurationMin("30");

        Screening screening = new Screening();
        screening.setStartTime(new Time(System.currentTimeMillis()));
        screening.setEndTime(new Time(System.currentTimeMillis() + milsInOneHour));

        MovieServiceImpl movieService = mock(MovieServiceImpl.class);
        MovieServiceImpl.setInstance(movieService);
        ScreeningServiceImpl screeningService = mock(ScreeningServiceImpl.class);
        ScreeningServiceImpl.setInstance(screeningService);

        when(request.getParameter("movie-name")).thenReturn(movie.getName());
        when(movieService.findMovieByName(movie.getName())).thenReturn(movie);
        when(requestUtil.getScreeningFromRequest(request)).thenReturn(screening);
        when(request.getParameter("movie-name")).thenReturn(movie.getName());
        when(request.getParameter("startTime")).thenReturn(screening.getStartTime().toString());
        when(request.getParameter("endTime")).thenReturn(screening.getEndTime().toString());
        when(screeningService.addScreening(screening)).thenReturn(true);

        Redirect redirect = (Redirect) commandAddScreening.execute(request, response);
        assertThat(redirect.isValid()).isTrue();
        assertThat(redirect.getCommand()).isEqualTo("admin");
    }

}
