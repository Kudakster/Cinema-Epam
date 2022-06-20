package com.epam.cinema.commands.open;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.enity.Seat;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CommandOpenScreeningPage implements ICommand {
    private final String PAGE = "screening";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        Integer screeningID = Integer.valueOf(request.getParameter("screening-id"));
        Map<Seat, Boolean> seatReserved = ServiceFactory.getSeatService().getSeatAvailableMap(screeningID);
        boolean result = seatReserved != null;

        if (result) {
            Set<Integer> rows = seatReserved.keySet().stream().map(Seat::getSeatRow).collect(Collectors.toCollection(TreeSet::new));
            request.setAttribute("rows", rows);
        }

        request.getSession().setAttribute("screening-id", screeningID);
        request.setAttribute("seatsAvailable", seatReserved);
        return new Forward(result, PAGE);
    }
}
