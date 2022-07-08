package com.epam.cinema.command;

import com.epam.cinema.commands.RequestUtil;
import com.epam.cinema.commands.Validation;
import com.epam.cinema.commands.add.CommandAddMovie;
import com.epam.cinema.commands.add.CommandAddNewTicket;
import com.epam.cinema.commands.add.CommandAddNewUser;
import com.epam.cinema.commands.add.CommandAddScreening;
import com.epam.cinema.enity.Movie;
import com.epam.cinema.enity.Screening;
import com.epam.cinema.enity.User;
import com.epam.cinema.service.implementation.MovieServiceImpl;
import com.epam.cinema.service.implementation.ScreeningServiceImpl;
import com.epam.cinema.service.implementation.TicketServiceImpl;
import com.epam.cinema.service.implementation.UserServiceImpl;
import com.epam.cinema.servlets.Redirect;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddCommandTest {
    @Mock
    private HttpSession session;
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

    MockedStatic<Validation> validationMockedStatic;

    @AfterEach
    public void close() {
        if (validationMockedStatic != null) {
            validationMockedStatic.close();
        }
    }

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
        assertThat(redirect.getErrorCommand()).isEqualTo("sign-up");
        assertThat(redirect.getErrorKey()).isNotEmpty();
    }

    @Test
    public void givenAddScreeningCommand_whenScreeningDataIsValid_thenReturnRedirect() {
        long milsInOneHour = 3_600_000;

        Movie movie = new Movie();
        movie.setName("Name");
        movie.setDurationMin("30");

        Screening screening = new Screening();
        screening.setScreeningID(1);
        screening.setAuditoriumID(1);
        screening.setMovieID(1);
        screening.setDate(new Date(System.currentTimeMillis()));
        screening.setStartTime(new Time(System.currentTimeMillis()));
        screening.setEndTime(new Time(System.currentTimeMillis() + milsInOneHour));

        validationMockedStatic = mockStatic(Validation.class);
        MovieServiceImpl movieService = mock(MovieServiceImpl.class);
        MovieServiceImpl.setInstance(movieService);
        ScreeningServiceImpl screeningService = mock(ScreeningServiceImpl.class);
        ScreeningServiceImpl.setInstance(screeningService);

        validationMockedStatic.when(() -> Validation.isMovieExist(request.getParameter("movie-name"))).thenReturn(true);
        when(requestUtil.getScreeningFromRequest(request)).thenReturn(screening);
        validationMockedStatic.when(() -> Validation.validate(screening)).thenReturn("");
        validationMockedStatic.when(() -> Validation.isDurationCorrectly(screening.getMovieID(), screening.getStartTime(), screening.getEndTime())).thenReturn(true);
        when(screeningService.addScreening(screening)).thenReturn(true);

        Redirect redirect = (Redirect) commandAddScreening.execute(request, response);
        assertThat(redirect.isValid()).isTrue();
        assertThat(redirect.getCommand()).isEqualTo("admin");
    }

    @Test
    public void givenAddScreeningCommand_whenScreeningDataHaveInvalidMovieName_thenReturnRedirect() {
        validationMockedStatic = mockStatic(Validation.class);
        validationMockedStatic.when(() -> Validation.isMovieExist(request.getParameter("movie-name"))).thenReturn(false);

        Redirect redirect = (Redirect) commandAddScreening.execute(request, response);
        assertThat(redirect.isValid()).isFalse();
        assertThat(redirect.getErrorCommand()).isEqualTo("admin");
        assertThat(redirect.getErrorKey()).isEqualTo("screening.update.error");
    }

    @Test
    public void givenAddScreeningCommand_whenScreeningDataHaveNullValue_thenReturnRedirect() {
        validationMockedStatic = mockStatic(Validation.class);
        validationMockedStatic.when(() -> Validation.isMovieExist(request.getParameter("movie-name"))).thenReturn(true);
        validationMockedStatic.when(() -> Validation.validate(any())).thenReturn("error.empty");

        Redirect redirect = (Redirect) commandAddScreening.execute(request, response);
        assertThat(redirect.isValid()).isFalse();
        assertThat(redirect.getErrorCommand()).isEqualTo("admin");
        assertThat(redirect.getErrorKey()).isEqualTo("error.empty");
    }

    @Test
    public void givenAddScreeningCommand_whenScreeningDurationSmallerThatMovieDuration_thenReturnRedirect() {
        validationMockedStatic = mockStatic(Validation.class);
        validationMockedStatic.when(() -> Validation.isMovieExist(request.getParameter("movie-name"))).thenReturn(true);
        validationMockedStatic.when(() -> Validation.validate(any())).thenReturn("");
        validationMockedStatic.when(() -> Validation.isDurationCorrectly(any(), any(), any())).thenReturn(false);
        when(requestUtil.getScreeningFromRequest(request)).thenReturn(new Screening());

        Redirect redirect = (Redirect) commandAddScreening.execute(request, response);
        assertThat(redirect.isValid()).isFalse();
        assertThat(redirect.getErrorCommand()).isEqualTo("admin");
        assertThat(redirect.getErrorKey()).isEqualTo("screening.update.duration");
    }

    @Test
    public void givenAddTicketCommand_whenTicketCanBeAdding_thenReturnRedirect() {
        TicketServiceImpl ticketService = mock(TicketServiceImpl.class);
        TicketServiceImpl.setInstance(ticketService);
        Map<String, String[]> map = new HashMap<>();
        map.put("seatID", new String[]{"1"});

        when(request.getParameterMap()).thenReturn(map);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(new User());
        when(request.getSession().getAttribute("screening-id")).thenReturn(1);
        when(ticketService.addTickets(eq(List.of(1)), eq(1), any())).thenReturn(true);

        Redirect redirect = (Redirect) commandAddNewTicket.execute(request, response);
        assertThat(redirect.isValid()).isTrue();
        assertThat(redirect.getCommand()).isEqualTo("user");
    }

    @Test
    public void givenAddTicketCommand_whenSeatIdIsNull_thenReturnRedirect() {
        TicketServiceImpl ticketService = mock(TicketServiceImpl.class);
        TicketServiceImpl.setInstance(ticketService);
        Map<String, String[]> map = new HashMap<>();
        map.put("seatID", null);

        when(request.getParameterMap()).thenReturn(map);

        Redirect redirect = (Redirect) commandAddNewTicket.execute(request, response);
        assertThat(redirect.isValid()).isFalse();
        assertThat(redirect.getErrorCommand()).isEqualTo("main");
    }
}
