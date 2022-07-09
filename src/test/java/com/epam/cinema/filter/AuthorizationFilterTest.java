package com.epam.cinema.filter;

import com.epam.cinema.enity.User;
import com.epam.cinema.enity.enumeration.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorizationFilterTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private HttpSession session;

    @InjectMocks
    private AuthorizationFilter authorizationFilter;
    @InjectMocks
    private User user;

    @BeforeEach
    public void setup() {
        user = new User();

    }

    @Test
    public void givenAuthorizationFilter_whenGuestIsPresent_thenDoFilter() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);
        when(request.getRequestURI()).thenReturn("/cinema/login");

        authorizationFilter.doFilter(request, response, filterChain);
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void givenAuthorizationFilter_whenGuestIsNotPresent_thenRedirectToMainPage() throws IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getRequestURI()).thenReturn("/cinema/login");

        authorizationFilter.doFilter(request, response, filterChain);
        verify(response, times(1)).sendRedirect("main");
    }

    @Test
    public void givenAuthorizationFilter_whenUserIsPresent_thenDoFilter() throws ServletException, IOException {
        user.setUserRole(UserRole.USER);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getRequestURI()).thenReturn("/cinema/user");

        authorizationFilter.doFilter(request, response, filterChain);
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void givenAuthorizationFilter_whenUserIsNotPresent_thenRedirectToMainPage() throws IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);
        when(request.getRequestURI()).thenReturn("/cinema/user");

        authorizationFilter.doFilter(request, response, filterChain);
        verify(response, times(1)).sendRedirect("main");
    }

    @Test
    public void givenAuthorizationFilter_whenAdminIsPresent_thenDoFilter() throws ServletException, IOException {
        user.setUserRole(UserRole.ADMIN);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getRequestURI()).thenReturn("/cinema/admin");

        authorizationFilter.doFilter(request, response, filterChain);
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void givenAuthorizationFilter_whenAdminIsNotPresent_thenRedirectToMainPage() throws IOException {
        user.setUserRole(UserRole.USER);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getRequestURI()).thenReturn("/cinema/admin");

        authorizationFilter.doFilter(request, response, filterChain);
        verify(response, times(1)).sendRedirect("main");
    }

    @Test
    public void givenAuthorizationFilter_whenUrlNotPresent_thenDoFilter() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/cinema/not");

        authorizationFilter.doFilter(request, response, filterChain);
        verify(filterChain, times(1)).doFilter(request, response);
    }
}
