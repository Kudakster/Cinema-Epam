package com.epam.cinema.command;

import com.epam.cinema.commands.CookieUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CookieUtilTest {
    @Mock
    private HttpServletRequest request;

    @Test
    public void givenCookieUtil_whenCookieIsPresent_thenReturnCommand() {
        Cookie[] cookies = new Cookie[]{new Cookie("cookie", "1234")};

        when(request.getCookies()).thenReturn(cookies);

        Integer cookieValue = CookieUtil.readCookie("cookie", request);
        assertThat(cookieValue).isEqualTo(1234);
    }

    @Test
    public void givenCookieUtil_whenCookieIsNotPresent_thenReturnCommand() {
        Cookie[] cookies = new Cookie[]{};

        when(request.getCookies()).thenReturn(cookies);

        Integer cookieValue = CookieUtil.readCookie("cookie", request);
        assertThat(cookieValue).isEqualTo(null);
    }

    @Test
    public void givenCookieUtil_whenCookieIsNull_thenReturnCommand() {
        when(request.getCookies()).thenReturn(null);

        Integer cookieValue = CookieUtil.readCookie("cookie", request);
        assertThat(cookieValue).isEqualTo(null);
    }
}
