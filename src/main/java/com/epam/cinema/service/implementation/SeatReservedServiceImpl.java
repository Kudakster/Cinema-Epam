package com.epam.cinema.service.implementation;

import com.epam.cinema.dao.implementation.DAOSeatReservedImpl;
import com.epam.cinema.enity.SeatReserved;
import com.epam.cinema.service.ISeatReservedService;

import java.util.List;

public class SeatReservedServiceImpl implements ISeatReservedService {
    private DAOSeatReservedImpl daoSeatReserved;
    private static SeatReservedServiceImpl instance = null;

    public SeatReservedServiceImpl() {
        daoSeatReserved = new DAOSeatReservedImpl();
    }

    @Override
    public SeatReserved findSeatReservedByID(Integer id) {
        return daoSeatReserved.getSeatReservedByID(id);
    }

    @Override
    public SeatReserved findSeatReservedBySeatIDAndScreeningID(Integer seatID, Integer screeningID) {
        return daoSeatReserved.getSeatReservedBySeatIDAndScreeningID(seatID, screeningID);
    }

    @Override
    public List<SeatReserved> findAllSeatReserved() {
        return daoSeatReserved.getAllSeats();
    }

    @Override
    public List<SeatReserved> findAllSeatReservedByScreeningID(Integer id) {
        return daoSeatReserved.getAllSeatsByScreeningID(id);
    }

    @Override
    public boolean addSeatReserved(SeatReserved seatReserved) {
        return daoSeatReserved.addSeat(seatReserved);
    }

    @Override
    public boolean deleteSeatReserved(SeatReserved seatReserved) {
        return daoSeatReserved.deleteSeat(seatReserved);
    }

    public boolean isSeatReservedExist(Integer seatID, Integer screeningID) {
        return findSeatReservedBySeatIDAndScreeningID(seatID, screeningID) != null;
    }

    public static SeatReservedServiceImpl getInstance() {
        if (instance == null)
            instance = new SeatReservedServiceImpl();
        return instance;
    }
}
