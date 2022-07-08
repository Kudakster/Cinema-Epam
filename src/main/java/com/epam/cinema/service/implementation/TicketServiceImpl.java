package com.epam.cinema.service.implementation;

import com.epam.cinema.dao.implementation.DAOTicketImpl;
import com.epam.cinema.enity.SeatReserved;
import com.epam.cinema.enity.Ticket;
import com.epam.cinema.service.ITicketService;
import com.epam.cinema.service.ServiceFactory;

import java.sql.Date;
import java.util.List;

public class TicketServiceImpl implements ITicketService {
    private DAOTicketImpl daoTicket;
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
        return daoTicket.getAllTicketByUserIDAndCurrentTime(id, date);
    }

    @Override
    public boolean addTicket(Ticket ticket) {
        return daoTicket.addTicket(ticket);
    }

    public boolean addTickets(List<Integer> seatID, Integer screeningID, Integer userID) {
        if (ServiceFactory.getSeatReservedService().isSeatsReservedNoneExists(seatID, screeningID)) {
            List<SeatReserved> seatReserved = ServiceFactory.getSeatReservedService().addSeatsReserved(seatID, screeningID);
            return seatReserved.stream()
                    .map(SeatReserved::getSeatReservedID)
                    .allMatch(id -> ServiceFactory.getTicketService()
                            .addTicket(new Ticket(userID, id)));
        } else  {
            return false;
        }
    }

    public int countTickets(Date date) {
        return daoTicket.countTickets(date);
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

    public static void setInstance(TicketServiceImpl instance) {
        TicketServiceImpl.instance = instance;
    }
}
