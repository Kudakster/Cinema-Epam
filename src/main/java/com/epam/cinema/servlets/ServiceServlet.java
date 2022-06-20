package com.epam.cinema.servlets;

import com.epam.cinema.commands.CommandResolver;
import com.epam.cinema.commands.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
@WebServlet("/serviceServlet")
public class ServiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        handleRequest(req, resp);
    }

    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        ICommand command = CommandResolver.getInstance().getCommand(req);
        Executor executor = command.execute(req, resp);

        try {
            executor.open(req, resp);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}