package com.epam.cinema.commands.open;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.enity.Auditorium;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CommandOpenAuditoriumPage implements ICommand {
    private final String PAGE = "auditorium";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("seats", ServiceFactory.getSeatService().findAllRowsAndSeats());
        request.getSession().setAttribute("auditorium", ServiceFactory.getAuditoriumService().findAllAuditoriums().get(0));
        return new Forward(PAGE);
    }
}
