package com.epam.cinema.dao.implementation;

import com.epam.cinema.dao.DAOGeneral;
import com.epam.cinema.dao.IDAOSeat;
import com.epam.cinema.dao.mysql.MySQLConstants;
import com.epam.cinema.dao.utils.Mapper;
import com.epam.cinema.enity.Seat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DAOSeatImpl extends DAOGeneral<Seat> implements IDAOSeat<Seat> {

    public DAOSeatImpl() {
        super(Seat::new);

        Mapper<ResultSet, Seat> seatMapper = (ResultSet resultSet, Seat seat) -> {
            seat.setSeatID(resultSet.getInt(1));
            seat.setAuditoriumID(resultSet.getInt(2));
            seat.setSeatRow(resultSet.getInt(3));
            seat.setSeatNumber(resultSet.getInt(4));
        };
        super.setObjectMapper(seatMapper);

        Mapper<Seat, PreparedStatement> statementMapper = (Seat seat, PreparedStatement preparedStatement) -> {
            preparedStatement.setInt(1, seat.getAuditoriumID());
            preparedStatement.setInt(2, seat.getSeatRow());
            preparedStatement.setInt(3, seat.getSeatNumber());
        };
        super.setStatementMapper(statementMapper);
    }

    @Override
    public Seat getSeatByID(Integer id) {
        return get(MySQLConstants.Seat.SQL_GET_SEAT_BY_ID, id);
    }

    @Override
    public Seat getSeatByRowAndNumber(Integer row, Integer number) {
        return get(MySQLConstants.Seat.SQL_GET_SEAT_BY_ROW_AND_NUMBER, row, number);
    }

    @Override
    public Seat getSeatByTicketID(Integer id) {
        return get(MySQLConstants.Seat.SQL_GET_SEAT_BY_TICKET_ID, id);
    }

    @Override
    public List<Seat> getAllSeats() {
        return getAll(MySQLConstants.Seat.SQL_GET_ALL_SEATS);
    }

    @Override
    public List<Seat> getAllSeatsByAuditoriumID(Integer id) {
        return getAllBy(MySQLConstants.Seat.SQL_GET_ALL_SEATS_BY_AUDITORIUM_ID, id);
    }

    @Override
    public boolean addSeat(Seat seat) {
        return add(seat, MySQLConstants.Seat.SQL_ADD_SEAT);
    }

    @Override
    public boolean addSeats(List<Seat> seatList) {
        return addMany(seatList, MySQLConstants.Seat.SQL_ADD_SEAT);
    }

    @Override
    public boolean deleteSeat(Seat seat) {
        return delete(seat.getSeatID(), MySQLConstants.Seat.SQL_DELETE_SEAT_BY_ID);
    }

    @Override
    public boolean deleteSeats(List<Seat> seatList) {
        String SQL_QUERY = MySQLConstants.Seat.SQL_DELETE_SEATS_BY_ID;
        for (int i = 0; i < seatList.size(); i++) {
            if (i == 0) {
                SQL_QUERY = SQL_QUERY.concat("(?");
            } else if (i == seatList.size() - 1) {
                SQL_QUERY = SQL_QUERY.concat(" ,?)");
            } else {
                SQL_QUERY = SQL_QUERY.concat(" ,?");
            }
        }

        List<Integer> list = seatList.stream()
                .map(Seat::getSeatID)
                .collect(Collectors.toList());
        return deleteMany(list, SQL_QUERY);
    }
}
