package com.epam.cinema.dao;

import java.util.List;

public interface IDAOSeatReserved<SeatReserved> {
    SeatReserved getSeatReservedByID(Integer id);

    SeatReserved getSeatReservedBySeatIDAndScreeningID(Integer seatID, Integer screeningID);

    List<SeatReserved> getAllSeats();

    List<SeatReserved> getAllSeatsByScreeningID(Integer id);

    boolean addSeat(SeatReserved seat);

    boolean updateSeatByID(SeatReserved seat);

    boolean deleteSeat(SeatReserved seat);
}
