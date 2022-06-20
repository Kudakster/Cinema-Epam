package com.epam.cinema.service.implementation;

import com.epam.cinema.dao.implementation.DAOTicketImpl;
import com.epam.cinema.enity.Ticket;
import com.epam.cinema.service.ITicketService;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class TicketServiceImpl implements ITicketService {
    private final DAOTicketImpl daoTicket;
    private static TicketServiceImpl instance = null;

    public TicketServiceImpl() {
        daoTicket = new DAOTicketImpl();
    }

    @Override
    public Ticket findTicketByID(Integer id) {
        return daoTicket.getTicketByID(id);
    }

    @Override
    public List<Ticket> findAllTickets() {
        return daoTicket.getAllTickets();
    }

    @Override
    public List<Ticket> findTicketsByUserID(Integer id) {
        return daoTicket.getAllTicketByUserID(id);
    }

    @Override
    public List<Ticket> findTicketsByUserIDAndCurrentTime(Integer id) {
        Date date = new Date(System.currentTimeMillis());
        Time time = new Time(System.currentTimeMillis());

        return daoTicket.getAllTicketByUserIDAndCurrentTime(id, date, time);
    }

    @Override
    public boolean addTicket(Ticket ticket) {
        return daoTicket.addTicket(ticket);
    }

    @Override
    public boolean deleteTicket(Ticket ticket) {
        return daoTicket.deleteTicket(ticket);
    }

    public static TicketServiceImpl getInstance() {
        if (instance == null)
            instance = new TicketServiceImpl();
        return instance;
    }
}
