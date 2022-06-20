package com.epam.cinema.service;

import com.epam.cinema.enity.Ticket;

import java.util.List;

public interface ITicketService {

    Ticket findTicketByID(Integer id);

    List<Ticket> findAllTickets();

    List<Ticket> findTicketsByUserID(Integer id);

    List<Ticket> findTicketsByUserIDAndCurrentTime(Integer id);

    boolean addTicket(Ticket ticket);

    boolean deleteTicket(Ticket ticket);
}
