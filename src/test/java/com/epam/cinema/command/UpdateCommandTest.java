package com.epam.cinema.command;

import com.epam.cinema.commands.RequestUtil;
import com.epam.cinema.commands.update.CommandUpdateAuditorium;
import com.epam.cinema.commands.update.CommandUpdateMovie;
import com.epam.cinema.commands.update.CommandUpdateUser;
import com.epam.cinema.enity.Auditorium;
import com.epam.cinema.enity.Movie;
import com.epam.cinema.enity.Screening;
import com.epam.cinema.enity.User;
import com.epam.cinema.service.implementation.AuditoriumServiceImpl;
import com.epam.cinema.service.implementation.MovieServiceImpl;
import com.epam.cinema.service.implementation.UserServiceImpl;
import com.epam.cinema.servlets.Redirect;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateCommandTest {
    @Mock
    private HttpSession session;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestUtil requestUtil;

    @InjectMocks
    private CommandUpdateAuditorium commandUpdateAuditorium;

    @InjectMocks
    private CommandUpdateMovie commandUpdateMovie;

    @InjectMocks
    private CommandUpdateUser commandUpdateUser;

    @Test
    public void givenUpdateAuditoriumCommand_whenUpdateAuditoriumIsTrue_thenReturnRedirect() {
        Auditorium auditorium = new Auditorium("auditoriumName");
        AuditoriumServiceImpl instance = mock(AuditoriumServiceImpl.class);
        AuditoriumServiceImpl.setInstance(instance);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("auditorium")).thenReturn(auditorium);
        when(request.getParameter("name")).thenReturn("auditoriumName");
        when(instance.updateAuditorium(auditorium)).thenReturn(true);

        Redirect redirect = (Redirect) commandUpdateAuditorium.execute(request, response);
        verify(request, atLeast(1)).getParameter("name");
        assertThat(redirect.isValid()).isTrue();
        assertThat(redirect.getCommand()).isEqualTo("admin");
    }

    @Test
    public void givenUpdateMovieCommand_whenUpdateMovieIsTrue_thenReturnRedirect() {
        Movie movie = new Movie();
        movie.setName("Jack");
        MovieServiceImpl instance = mock(MovieServiceImpl.class);
        MovieServiceImpl.setInstance(instance);

        when(requestUtil.getMovieFromRequest(request)).thenReturn(movie);
        when(request.getParameter("id")).thenReturn("1");
        when(instance.updateMovie(movie)).thenReturn(true);

        Redirect redirect = (Redirect) commandUpdateMovie.execute(request, response);
        assertThat(redirect.isValid()).isTrue();
        assertThat(redirect.getCommand()).isEqualTo("admin");
    }

    @Test
    public void givenUpdateUserCommand_whenUpdateUserIsTrue_thenReturnRedirect() {
        UserServiceImpl instance = mock(UserServiceImpl.class);
        UserServiceImpl.setInstance(instance);
        User user= new User();
        user.setLogin("userLogin");
        user.setFirstName("userFirstname");
        user.setSurName("userSurname");

        when(request.getParameter("login")).thenReturn("userLogin");
        when(request.getParameter("firstname")).thenReturn("userFirstname");
        when(request.getParameter("surname")).thenReturn("userSurname");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(new User());
        when(instance.updateUser(user)).thenReturn(true);

        Redirect redirect = (Redirect) commandUpdateUser.execute(request, response);
        assertThat(redirect.isValid()).isTrue();
        assertThat(redirect.getCommand()).isEqualTo("user");
    }
}
