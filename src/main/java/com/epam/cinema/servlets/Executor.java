package com.epam.cinema.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Executor {
    void open(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
}
