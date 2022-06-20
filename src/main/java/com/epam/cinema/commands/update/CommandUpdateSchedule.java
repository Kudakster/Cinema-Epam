package com.epam.cinema.commands.update;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.servlets.Executor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandUpdateSchedule implements ICommand {
    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
