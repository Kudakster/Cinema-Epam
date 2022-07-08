package com.epam.cinema.commands.open;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.service.implementation.TicketServiceImpl;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class CommandOpenStatisticPage implements ICommand {
    private final String PAGE = "statistic";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        long milsInOneWeek = 604800000L;
        long milsInOneMonth = 2629800000L;

        TicketServiceImpl ticketService = ServiceFactory.getTicketService();
        int ticketsSoldForTheDay = ticketService.countTickets(new Date(System.currentTimeMillis()));
        int ticketsSoldForThePastWeek = ticketService.countTickets(new Date(System.currentTimeMillis() - milsInOneWeek));
        int ticketsSoldForThePastMonth = ticketService.countTickets(new Date(System.currentTimeMillis() - milsInOneMonth));

        request.setAttribute("ticketsSoldForTheDay", ticketsSoldForTheDay);
        request.setAttribute("ticketsSoldForThePastWeek", ticketsSoldForThePastWeek);
        request.setAttribute("ticketsSoldForThePastMonth", ticketsSoldForThePastMonth);
        return new Forward(PAGE);
    }
}
