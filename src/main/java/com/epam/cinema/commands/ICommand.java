package com.epam.cinema.commands;

import com.epam.cinema.servlets.Executor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
    Executor execute(HttpServletRequest request, HttpServletResponse response);
}
