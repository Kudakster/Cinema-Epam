package com.epam.cinema.dao.implementation;

import com.epam.cinema.dao.DAOGeneral;
import com.epam.cinema.dao.IDAOSeatReserved;
import com.epam.cinema.dao.mysql.MySQLConstants;
import com.epam.cinema.dao.utils.Mapper;
import com.epam.cinema.enity.SeatReserved;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DAOSeatReservedImpl extends DAOGeneral<SeatReserved> implements IDAOSeatReserved<SeatReserved> {

    public DAOSeatReservedImpl() {
        super(SeatReserved::new);

        Mapper<ResultSet, SeatReserved> seatReservedMapper = (ResultSet resultSet, SeatReserved seatReserved) -> {
            seatReserved.setSeatReservedID(resultSet.getInt(1));
            seatReserved.setSeatID(resultSet.getInt(2));
            seatReserved.setScreeningID(resultSet.getInt(3));
        };
        super.setObjectMapper(seatReservedMapper);

        Mapper<SeatReserved, PreparedStatement> statementMapper = (SeatReserved seatReserved, PreparedStatement preparedStatement) -> {
            preparedStatement.setInt(1, seatReserved.getSeatID());
            preparedStatement.setInt(2, seatReserved.getScreeningID());
        };
        super.setStatementMapper(statementMapper);
    }

    @Override
    public SeatReserved getSeatReservedByID(Integer id) {
        return get(MySQLConstants.SeatReserved.SQL_GET_SEAT_RESERVED_BY_ID, id);
    }

    @Override
    public SeatReserved getSeatReservedBySeatIDAndScreeningID(Integer seatID, Integer screeningID) {
        return get(MySQLConstants.SeatReserved.SQL_GET_SEAT_RESERVED_BY_SEAT_ID_AND_SCREENING_ID, seatID, screeningID);
    }

    @Override
    public List<SeatReserved> getAllSeats() {
        return getAll(MySQLConstants.SeatReserved.SQL_GET_ALL_SEAT_RESERVED);
    }

    @Override
    public List<SeatReserved> getAllSeatsByScreeningID(Integer id) {
        return getAllBy(MySQLConstants.SeatReserved.SQL_GET_ALL_SEAT_RESERVED_BY_SCREENING_ID, id);
    }

    @Override
    public boolean addSeat(SeatReserved seatReserved) {
        return add(seatReserved, MySQLConstants.SeatReserved.SQL_ADD_SEAT_RESERVED);
    }

    @Override
    public boolean updateSeatByID(SeatReserved seatReserved) {
        return update(seatReserved, MySQLConstants.SeatReserved.SQL_UPDATE_SEAT_RESERVED_BY_ID, 3, seatReserved.getSeatReservedID());
    }

    @Override
    public boolean deleteSeat(SeatReserved seatReserved) {
        return delete(seatReserved.getSeatReservedID(), MySQLConstants.SeatReserved.SQL_DELETE_SEAT_RESERVED_BY_ID);
    }
}
