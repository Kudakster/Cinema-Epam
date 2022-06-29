package com.epam.cinema.command;

import com.epam.cinema.commands.CookieUtil;
import com.epam.cinema.commands.add.CommandAddMovie;
import com.epam.cinema.commands.open.*;
import com.epam.cinema.enity.Auditorium;
import com.epam.cinema.enity.Movie;
import com.epam.cinema.enity.Seat;
import com.epam.cinema.service.implementation.AuditoriumServiceImpl;
import com.epam.cinema.service.implementation.MovieServiceImpl;
import com.epam.cinema.service.implementation.ScreeningServiceImpl;
import com.epam.cinema.service.implementation.SeatServiceImpl;
import com.epam.cinema.servlets.Forward;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OpenCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @InjectMocks
    private CommandOpenAddMoviePage commandOpenAddMoviePage;

    @InjectMocks
    private CommandOpenAdminPage commandOpenAdminPage;

    @InjectMocks
    private CommandOpenAuditoriumPage commandOpenAuditoriumPage;

    @InjectMocks
    private CommandOpenListOfMoviesPage commandOpenListOfMoviesPage;

    @InjectMocks
    private CommandOpenLoginPage commandOpenLoginPage;

    @InjectMocks
    private CommandOpenMainPage commandOpenMainPage;

    @InjectMocks
    private CommandOpenScreeningPage commandOpenScreeningPage;

    @InjectMocks
    private CommandOpenSignUpPage commandOpenSignUpPage;

    @InjectMocks
    private CommandOpenUpdateMoviePage commandOpenUpdateMoviePage;


    @Test
    public void givenOpenAddMoviePageCommand_whenOpenWithSuccess_thenReturnForward() {
        Forward forward = (Forward) commandOpenAddMoviePage.execute(request, response);
        assertThat(forward.isValid()).isTrue();
        assertThat(forward.getPage()).isEqualTo("addMovie");
    }

    @Test
    public void givenOpenAdminPageCommand_whenOpenWithSuccess_thenReturnForward() {
        Forward forward = (Forward) commandOpenAdminPage.execute(request, response);
        assertThat(forward.isValid()).isTrue();
        assertThat(forward.getPage()).isEqualTo("admin");
    }

    @Test
    public void givenOpenAuditoriumPageCommand_whenOpenWithSuccess_thenReturnForward() {
        SeatServiceImpl seatService = mock(SeatServiceImpl.class);
        SeatServiceImpl.setInstance(seatService);
        AuditoriumServiceImpl auditoriumService = mock(AuditoriumServiceImpl.class);
        AuditoriumServiceImpl.setInstance(auditoriumService);

        when(request.getSession()).thenReturn(session);
        when(seatService.findAllRowsAndSeats()).thenReturn(new HashMap<>());
        when(auditoriumService.findAllAuditoriums()).thenReturn(List.of(new Auditorium()));

        Forward forward = (Forward) commandOpenAuditoriumPage.execute(request, response);
        assertThat(forward.isValid()).isTrue();
        assertThat(forward.getPage()).isEqualTo("auditorium");
    }

    @Test
    public void givenOpenMoviesCommand_whenOpenWithSuccess_thenReturnForward() {
        MockedStatic<CookieUtil> cookieUtilMockedStatic = Mockito.mockStatic(CookieUtil.class);
        MovieServiceImpl movieService = mock(MovieServiceImpl.class);
        MovieServiceImpl.setInstance(movieService);

        cookieUtilMockedStatic.when(() -> CookieUtil.readCookie("missedMovies", request)).thenReturn(0);
        cookieUtilMockedStatic.when(() -> CookieUtil.readCookie("numberMovies", request)).thenReturn(5);
        when(movieService.findMoviesByPagination(0, 5)).thenReturn(List.of(new Movie()));
        when(movieService.countAllMovies()).thenReturn(2);

        Forward forward = (Forward) commandOpenListOfMoviesPage.execute(request, response);
        assertThat(forward.isValid()).isTrue();
        assertThat(forward.getPage()).isEqualTo("movies");
    }

    @Test
    public void givenOpenLoginPageCommand_whenOpenWithSuccess_thenReturnForward() {
        Forward forward = (Forward) commandOpenLoginPage.execute(request, response);
        assertThat(forward.isValid()).isTrue();
        assertThat(forward.getPage()).isEqualTo("sign-in");
    }

    @Test
    public void givenOpenMainPageCommand_whenOpenWithSuccess_thenReturnForward() {
        ScreeningServiceImpl screeningService = mock(ScreeningServiceImpl.class);
        ScreeningServiceImpl.setInstance(screeningService);

        when(request.getParameter("date")).thenReturn("");
        when(screeningService.getGroupedMapScreeningByMovie(any())).thenReturn(new HashMap<>());

        Forward forward = (Forward) commandOpenMainPage.execute(request, response);
        assertThat(forward.isValid()).isTrue();
        assertThat(forward.getPage()).isEqualTo("main");
    }

    @Test
    public void givenOpenScreeningPageCommand_whenOpenWithSuccess_thenReturnForward() {
        Seat seat = new Seat();
        seat.setSeatRow(1);

        Map<Seat, Boolean> map = new HashMap<>();
        map.put(seat, false);

        SeatServiceImpl seatService = mock(SeatServiceImpl.class);
        SeatServiceImpl.setInstance(seatService);

        when(request.getParameter("screening-id")).thenReturn("1");
        when(seatService.getSeatAvailableMap(1)).thenReturn(map);
        when(request.getSession()).thenReturn(session);

        Forward forward = (Forward) commandOpenScreeningPage.execute(request, response);
        assertThat(forward.isValid()).isTrue();
        assertThat(forward.getPage()).isEqualTo("screening");
    }

    @Test
    public void givenOpenSignUpPageCommand_whenOpenWithSuccess_thenReturnForward() {
        Forward forward = (Forward) commandOpenSignUpPage.execute(request, response);
        assertThat(forward.isValid()).isTrue();
        assertThat(forward.getPage()).isEqualTo("sign-up");
    }

    @Test
    public void givenOpenUpdateMoviePageCommand_whenOpenWithSuccess_thenReturnForward() {
        MovieServiceImpl movieService = mock(MovieServiceImpl.class);
        MovieServiceImpl.setInstance(movieService);

        when(request.getParameter("u-movie-id")).thenReturn("1");
        when(movieService.findMovieById(1)).thenReturn(new Movie());

        Forward forward = (Forward) commandOpenUpdateMoviePage.execute(request, response);
        assertThat(forward.isValid()).isTrue();
        assertThat(forward.getPage()).isEqualTo("updateMovie");
    }

    @Test
    public void givenOpenUpdateMoviePageCommand_whenOpenWithError_thenReturnForward() {
        MovieServiceImpl movieService = mock(MovieServiceImpl.class);
        MovieServiceImpl.setInstance(movieService);

        when(request.getParameter("u-movie-id")).thenReturn("1");
        when(movieService.findMovieById(1)).thenReturn(null);

        Forward forward = (Forward) commandOpenUpdateMoviePage.execute(request, response);
        assertThat(forward.isValid()).isFalse();
        assertThat(forward.getErrorPage()).isEqualTo("admin");
    }
}
