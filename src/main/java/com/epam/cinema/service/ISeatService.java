package com.epam.cinema.service;

import com.epam.cinema.enity.Seat;

import java.util.List;
import java.util.Map;

public interface ISeatService {

    Seat findSeatByID(Integer id);

    Seat findSeatByTicketID(Integer id);

    List<Seat> findAllSeats();

    List<Seat> findAllSeatsByAuditoriumID(Integer id);

    Map<Integer, Long> findAllRowsAndSeats();

    boolean addSeat(Seat movie);

    boolean addSeats(List<Seat> seatList);

    boolean updateSeats(List<Seat> seatList);

    boolean deleteSeat(Seat movie);

    boolean deleteSeats(List<Seat> seatList);
}
