package com.epam.cinema.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface IDAOTicket<Ticket> {
    Ticket getTicketByID(Integer id);

    List<Ticket> getAllTickets();

    List<Ticket> getAllTicketByUserID(Integer userID);

    List<Ticket> getAllTicketByUserIDAndCurrentTime(Integer userID, Date date);

    boolean addTicket(Ticket seat);

    boolean updateTicketByID(Ticket seat);

    boolean deleteTicket(Ticket seat);

    int countTickets(Date date);
}
