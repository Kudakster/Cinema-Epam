package com.epam.cinema.commands;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

public class CookieUtil {
    public static Integer readCookie(String key, HttpServletRequest request) {
        return Integer.valueOf(Objects.requireNonNull(Objects.requireNonNull(Arrays.stream(request.getCookies()).unordered())
                .filter(c -> key.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny().orElse(null)));
    }
}
