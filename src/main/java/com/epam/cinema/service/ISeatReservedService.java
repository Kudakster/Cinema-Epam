package com.epam.cinema.service;

import com.epam.cinema.enity.SeatReserved;

import java.util.List;

public interface ISeatReservedService {

    SeatReserved findSeatReservedByID(Integer id);

    SeatReserved findSeatReservedBySeatIDAndScreeningID(Integer seatID, Integer screeningID);

    List<SeatReserved> findAllSeatReserved();

    List<SeatReserved> findAllSeatReservedByScreeningID(Integer id);

    boolean addSeatReserved(SeatReserved seatReserved);

    boolean deleteSeatReserved(SeatReserved seatReserved);
}
