package com.epam.cinema.service.implementation;

import com.epam.cinema.dao.implementation.DAOSeatImpl;
import com.epam.cinema.enity.Seat;
import com.epam.cinema.enity.SeatReserved;
import com.epam.cinema.service.ISeatService;
import com.epam.cinema.service.ServiceFactory;

import java.sql.Date;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SeatServiceImpl implements ISeatService {
    private final DAOSeatImpl daoSeat;
    private static SeatServiceImpl instance = null;

    public SeatServiceImpl() {
        daoSeat = new DAOSeatImpl();
    }

    @Override
    public Seat findSeatByID(Integer id) {
        return daoSeat.getSeatByID(id);
    }

    @Override
    public Seat findSeatByTicketID(Integer id) {
        return daoSeat.getSeatByTicketID(id);
    }

    @Override
    public List<Seat> findAllSeats() {
        return daoSeat.getAllSeats();
    }

    @Override
    public List<Seat> findAllSeatsByAuditoriumID(Integer id) {
        return daoSeat.getAllSeatsByAuditoriumID(id);
    }

    @Override
    public Map<Integer, Long> findAllRowsAndSeats() {
        List<Seat> seats = daoSeat.getAllSeats();

        if (seats != null) {
            return seats.stream()
                    .map(Seat::getSeatRow)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        }

        return null;
    }

    @Override
    public boolean addSeat(Seat seat) {
        return daoSeat.addSeat(seat);
    }

    @Override
    public boolean deleteSeat(Seat seat) {
        return daoSeat.deleteSeat(seat);
    }

    @Override
    public boolean updateSeats(List<Seat> seatList) {
        if (seatList == null) {
            return false;
        }

        if (isAuditoriumHaveActiveScreening(seatList.get(0).getAuditoriumID())) {
            return false;
        }

        return deleteSeats(seatList) & addSeats(seatList);
    }

    @Override
    public boolean addSeats(List<Seat> seatList) {
        seatList.removeIf(seat -> daoSeat.getSeatByRowAndNumber(seat.getSeatRow(), seat.getSeatNumber()) != null);
        if (seatList.size() != 0) {
            return daoSeat.addSeats(seatList);
        }
        return true;
    }

    @Override
    public boolean deleteSeats(List<Seat> seatList) {
        Set<Integer> setRowAndNumber = seatList.stream()
                .map(seat -> Integer.parseInt(seat.getSeatRow() + "" + seat.getSeatNumber()))
                .collect(Collectors.toSet());
        List<Seat> deleteSeatsList = daoSeat.getAllSeats();

        if (deleteSeatsList != null) {
            deleteSeatsList.removeIf(seat -> setRowAndNumber.stream()
                    .anyMatch(num -> num == Integer.parseInt(seat.getSeatRow() + "" + seat.getSeatNumber())));
            if (deleteSeatsList.size() == 1) {
                return daoSeat.deleteSeat(deleteSeatsList.get(0));
            } else if (deleteSeatsList.size() >= 1) {
                return daoSeat.deleteSeats(deleteSeatsList);
            }
        }
        return true;
    }

    public Map<Seat, Boolean> getSeatAvailableMap(Integer screeningID) {
        Integer auditoriumID = 1;
        List<Seat> seatList = findAllSeatsByAuditoriumID(auditoriumID);
        List<SeatReserved> seatReservedList = ServiceFactory.getSeatReservedService().findAllSeatReservedByScreeningID(screeningID);
        Map<Seat, Boolean> seatMap = new LinkedHashMap<>();
        if (seatReservedList != null) {
            seatList.forEach(seat -> {
                if (seatReservedList.stream().anyMatch(seatReserved -> seatReserved.getSeatID().equals(seat.getSeatID()))) {
                    seatMap.put(seat, false);
                } else {
                    seatMap.put(seat, true);
                }
            });
        } else {
            seatList.forEach(seat -> seatMap.put(seat, true));
        }
        return seatMap;
    }

    private boolean isAuditoriumHaveActiveScreening(Integer id) {
        return ServiceFactory.getScreeningService().findScreeningByDateAndAuditoriumID(new Date(System.currentTimeMillis()), id) != null;
    }

    public static SeatServiceImpl getInstance() {
        if (instance == null)
            instance = new SeatServiceImpl();
        return instance;
    }
}
