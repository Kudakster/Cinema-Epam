package com.epam.cinema.commands;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class CookieUtil {
    public static Integer readCookie(String key, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }

        String cookieValue = Arrays.stream(cookies)
                .filter(c -> key.equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst().orElse(null);

        return cookieValue != null ? Integer.parseInt(cookieValue) : null;
    }
}
