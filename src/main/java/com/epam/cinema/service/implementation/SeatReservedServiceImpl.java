package com.epam.cinema.service.implementation;

import com.epam.cinema.dao.implementation.DAOSeatReservedImpl;
import com.epam.cinema.enity.SeatReserved;
import com.epam.cinema.service.ISeatReservedService;
import com.epam.cinema.service.ServiceFactory;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<SeatReserved> addSeatsReserved(List<Integer> seatID, Integer screeningID) {
       seatID.forEach(id -> ServiceFactory.getSeatReservedService().addSeatReserved(new SeatReserved(id, screeningID)));

        return seatID.stream()
                .map(id -> ServiceFactory.getSeatReservedService()
                        .findSeatReservedBySeatIDAndScreeningID(id, screeningID))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteSeatReserved(SeatReserved seatReserved) {
        return daoSeatReserved.deleteSeat(seatReserved);
    }

    public boolean isSeatReservedExist(Integer seatID, Integer screeningID) {
        return findSeatReservedBySeatIDAndScreeningID(seatID, screeningID) != null;
    }

    public boolean isSeatsReservedNoneExists(List<Integer> seatID, Integer screeningID) {
        return seatID.stream().noneMatch(id -> ServiceFactory.getSeatReservedService().isSeatReservedExist(id, screeningID));
    }

    public static SeatReservedServiceImpl getInstance() {
        if (instance == null)
            instance = new SeatReservedServiceImpl();
        return instance;
    }
}
