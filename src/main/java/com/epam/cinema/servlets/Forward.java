package com.epam.cinema.servlets;

import com.epam.cinema.config.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Forward implements Executor {
    private boolean isValid = true;
    private final String page;
    private String errorPage;

    public Forward(String page) {
        this.page = page;
    }

    public Forward(boolean isValid, String page) {
        this.isValid = isValid;
        this.page = page;
    }

    public Forward(boolean isValid, String page, String errorPage) {
        this.isValid = isValid;
        this.page = page;
        this.errorPage = errorPage;
    }

    @Override
    public void open(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isValid) {
            request.getRequestDispatcher(Configuration.getInstance().getPage(page)).forward(request, response);
        } else {
            request.getRequestDispatcher(Configuration.getInstance().getPage(errorPage)).forward(request, response);
        }

    }
}
