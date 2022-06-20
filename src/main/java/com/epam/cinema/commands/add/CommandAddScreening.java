package com.epam.cinema.commands.add;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.servlets.Executor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandAddScreening implements ICommand {
    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {

        return null;
    }
}
