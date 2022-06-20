package com.epam.cinema.commands.update;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.enity.Auditorium;
import com.epam.cinema.enity.Seat;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

public class CommandUpdateSeats implements ICommand {
    private final String COMMAND = "admin";
    private final String ERROR_COMMAND = "admin";
    private final String ERROR_KEY = "auditorium.error";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        List<Seat> seatList = new LinkedList<>();
        Auditorium auditorium = (Auditorium) request.getSession().getAttribute("auditorium");
        String[] array = request.getParameterMap().get("seat-number");

        for (int i = 1; i <= array.length; i++) {
            int seatNumber = Integer.parseInt(array[i - 1]);
            for (int j = 1; j <= seatNumber; j++) {
                seatList.add(new Seat(i, j, auditorium.getAuditoriumID()));
            }
        }

        return new Redirect(ServiceFactory.getSeatService().updateSeats(seatList), COMMAND, ERROR_COMMAND, ERROR_KEY);
    }
}