package com.epam.cinema.command;

import com.epam.cinema.commands.CommandLogout;
import com.epam.cinema.commands.CommandValidateUser;
import com.epam.cinema.commands.Validation;
import com.epam.cinema.enity.User;
import com.epam.cinema.service.implementation.UserServiceImpl;
import com.epam.cinema.servlets.Redirect;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OtherCommandTest {
    @Mock
    private HttpSession session;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private CommandLogout commandLogout;

    @InjectMocks
    private CommandValidateUser commandValidateUser;

    @Test
    public void givenLogoutCommand_whenSessionExist_thenReturnForward() {
        when(request.getSession()).thenReturn(session);

        Redirect redirect = (Redirect) commandLogout.execute(request, response);
        assertThat(redirect.isValid()).isTrue();
        assertThat(redirect.getCommand()).isEqualTo("main");
    }

    @Test
    public void givenValidateUserCommand_whenUserIsValid_thenReturnForward() {
        MockedStatic<Validation> validationMockedStatic = mockStatic(Validation.class);
        UserServiceImpl userService = mock(UserServiceImpl.class);
        UserServiceImpl.setInstance(userService);

        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
        validationMockedStatic.when(() -> Validation.validate(any())).thenReturn("");
        when(userService.verifyUserAndReturnUser(any())).thenReturn(new User());
        when(request.getSession()).thenReturn(session);

        Redirect redirect = (Redirect) commandValidateUser.execute(request, response);
        assertThat(redirect.isValid()).isTrue();
        assertThat(redirect.getCommand()).isEqualTo("user");

        validationMockedStatic.close();
    }

    @Test
    public void givenValidateUserCommand_whenUserIsNotValid_thenReturnForward() {
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");

        Redirect redirect = (Redirect) commandValidateUser.execute(request, response);
        assertThat(redirect.isValid()).isFalse();
        assertThat(redirect.getErrorCommand()).isEqualTo("login");
        assertThat(redirect.getErrorKey()).isNotEmpty();
    }

    @Test
    public void givenValidateUserCommand_whenUserIsNotFound_thenReturnForward() {
        MockedStatic<Validation> validationMockedStatic = mockStatic(Validation.class);
        UserServiceImpl userService = mock(UserServiceImpl.class);
        UserServiceImpl.setInstance(userService);

        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
        validationMockedStatic.when(() -> Validation.validate(any())).thenReturn("");
        when(userService.verifyUserAndReturnUser(any())).thenReturn(null);

        Redirect redirect = (Redirect) commandValidateUser.execute(request, response);
        assertThat(redirect.isValid()).isFalse();
        assertThat(redirect.getErrorCommand()).isEqualTo("login");
        assertThat(redirect.getErrorKey()).isEqualTo("user.notFound");

        validationMockedStatic.close();
    }
}
