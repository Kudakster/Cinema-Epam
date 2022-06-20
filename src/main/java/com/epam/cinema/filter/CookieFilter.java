package com.epam.cinema.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class CookieFilter extends HttpFilter implements Filter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Map<String, String> cookieMap = new HashMap<>();
        cookieMap.put("missedMovies", "0");
        cookieMap.put("numberMovies", "5");
        cookieMap.put("numberAllMovies", "");
        cookieMap.put("page", "movies");
        cookieMap.put("cookieLocale", "en");

        List<Cookie> listCookie = Objects.nonNull(req.getCookies()) ? List.of(req.getCookies()) : List.of();
        cookieMap.forEach((cookieName, value) -> {
            if (listCookie.stream().noneMatch(cookie -> cookieName.equals(cookie.getName()))) {
                Cookie cookie = new Cookie(cookieName, value);
                cookie.setSecure(true);
                res.addCookie(cookie);
            }
        });

        res.addCookie(new Cookie("error", ""));
        chain.doFilter(req, res);
    }
}
