package com.epam.cinema.dao.implementation;

import com.epam.cinema.dao.DAOGeneral;
import com.epam.cinema.dao.IDAOTicket;
import com.epam.cinema.dao.mysql.MySQLConstants;
import com.epam.cinema.dao.utils.Mapper;
import com.epam.cinema.enity.Ticket;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.List;

public class DAOTicketImpl extends DAOGeneral<Ticket> implements IDAOTicket<Ticket> {

    public DAOTicketImpl() {
        super(Ticket::new);

        Mapper<ResultSet, Ticket> ticketMapper = (ResultSet resultSet, Ticket ticket) -> {
            ticket.setTicketID(resultSet.getInt(1));
            ticket.setUserID(resultSet.getInt(2));
            ticket.setSearReservedID(resultSet.getInt(3));
        };
        super.setObjectMapper(ticketMapper);

        Mapper<Ticket, PreparedStatement> statementMapper = (Ticket ticket, PreparedStatement preparedStatement) -> {
            preparedStatement.setInt(1, ticket.getUserID());
            preparedStatement.setInt(2, ticket.getSeatReservedID());
        };
        super.setStatementMapper(statementMapper);
    }

    @Override
    public Ticket getTicketByID(Integer id) {
        return get(MySQLConstants.Ticket.SQL_GET_TICKET_BY_ID, id);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return getAll(MySQLConstants.Ticket.SQL_GET_ALL_TICKETS);
    }

    @Override
    public List<Ticket> getAllTicketByUserID(Integer userID) {
        return getAllBy(MySQLConstants.Ticket.SQL_GET_ALL_TICKETS_BY_USER_ID, userID);
    }

    @Override
    public List<Ticket> getAllTicketByUserIDAndCurrentTime(Integer userID, Date date) {
        return getAllBy(MySQLConstants.Ticket.SQL_GET_ALL_TICKETS_BY_USER_ID_AND_CURRENT_DATE, userID, date.toString());
    }

    @Override
    public boolean addTicket(Ticket ticket) {
        return add(ticket, MySQLConstants.Ticket.SQL_ADD_TICKET);
    }

    @Override
    public boolean updateTicketByID(Ticket ticket) {
        return update(ticket, MySQLConstants.Ticket.SQL_UPDATE_TICKET_BY_ID, 3, ticket.getTicketID());
    }

    @Override
    public boolean deleteTicket(Ticket ticket) {
        return delete(ticket.getTicketID(), MySQLConstants.Ticket.SQL_DELETE_TICKET_BY_ID);
    }

    @Override
    public int countTickets(Date date) {
        return count(MySQLConstants.Ticket.SQL_COUNT_TICKETS, date.toString());
    }
}
