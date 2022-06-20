package com.epam.cinema.commands.open;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandOpenChangePasswordPage implements ICommand {
    private final String PAGE = "changePassword";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        return new Forward(PAGE);
    }
}
