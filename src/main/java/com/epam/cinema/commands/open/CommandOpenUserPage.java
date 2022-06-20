package com.epam.cinema.commands.open;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.enity.*;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandOpenUserPage implements ICommand {
    private final String PAGE = "user";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Integer id = user.getId();

        List<Ticket> ticketList = ServiceFactory.getTicketService().findTicketsByUserIDAndCurrentTime(id);

        if (ticketList != null) {
            Map<Ticket, Auditorium> auditoriumMap = new HashMap<>();
            Map<Ticket, Movie> movieMap = new HashMap<>();
            Map<Ticket, Seat> seatMap = new HashMap<>();
            Map<Ticket, Screening> screeningMap = new HashMap<>();

            ticketList.forEach(ticket -> {
                auditoriumMap.put(ticket, ServiceFactory.getAuditoriumService().findAuditoriumByTicketID(ticket.getTicketID()));
                movieMap.put(ticket, ServiceFactory.getMovieService().findMovieByTicketID(ticket.getTicketID()));
                seatMap.put(ticket, ServiceFactory.getSeatService().findSeatByTicketID(ticket.getTicketID()));
                screeningMap.put(ticket, ServiceFactory.getScreeningService().findScreeningByTicketID(ticket.getTicketID()));
            });

            request.setAttribute("ticketList", ticketList);
            request.setAttribute("auditoriumMap", auditoriumMap);
            request.setAttribute("movieMap", movieMap);
            request.setAttribute("seatMap", seatMap);
            request.setAttribute("screeningMap", screeningMap);
        }

        return new Forward(PAGE);
    }
}
