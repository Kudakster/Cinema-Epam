package com.epam.cinema.commands;

import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandLogout implements ICommand{
    private final String COMMAND = "main";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return new Redirect(COMMAND);
    }
}
