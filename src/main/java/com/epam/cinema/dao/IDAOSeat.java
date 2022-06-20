package com.epam.cinema.dao;

import java.util.List;

public interface IDAOSeat<Seat> {
    Seat getSeatByID(Integer id);

    Seat getSeatByTicketID(Integer id);

    Seat getSeatByRowAndNumber(Integer row, Integer number);

    List<Seat> getAllSeats();

    List<Seat> getAllSeatsByAuditoriumID(Integer id);

    boolean addSeat(Seat seat);

    boolean addSeats(List<Seat> seatList);

    boolean deleteSeat(Seat seat);

    boolean deleteSeats(List<Seat> seatList);
}
