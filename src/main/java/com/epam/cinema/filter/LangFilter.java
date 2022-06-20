package com.epam.cinema.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LangFilter extends HttpFilter {
    private static final Logger log = Logger.getLogger(LangFilter.class);
    private static final String LOCALE = "locale";
    private static final String EN = "en";

    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        if (httpSession.getAttribute(LOCALE) == null) {
            httpSession.setAttribute(LOCALE, EN);
        } else if (req.getParameter(LOCALE) != null) {
            httpSession.setAttribute(LOCALE, req.getParameter(LOCALE));
        }

        chain.doFilter(req, res);
    }
}
