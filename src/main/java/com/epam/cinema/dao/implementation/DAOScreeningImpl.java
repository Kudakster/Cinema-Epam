package com.epam.cinema.dao.implementation;

import com.epam.cinema.dao.DAOGeneral;
import com.epam.cinema.dao.IDAOScreening;
import com.epam.cinema.dao.mysql.MySQLConstants;
import com.epam.cinema.dao.utils.Mapper;
import com.epam.cinema.enity.Screening;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

public class DAOScreeningImpl extends DAOGeneral<Screening> implements IDAOScreening<Screening> {

    public DAOScreeningImpl() {
        super(Screening::new);
        Mapper<ResultSet, Screening> screeningMapper = (ResultSet resultSet, Screening screening) -> {
            screening.setScreeningID(resultSet.getInt("screening_id"));
            screening.setAuditoriumID(resultSet.getInt("auditorium_id"));
            screening.setMovieID(resultSet.getInt("movie_id"));
            screening.setDate(resultSet.getDate("screening_date"));
            screening.setStartTime(resultSet.getTime("screening_start_time"));
            screening.setEndTime(resultSet.getTime("screening_end_time"));
        };
        super.setObjectMapper(screeningMapper);

        Mapper<Screening, PreparedStatement> statementMapper = (Screening screening, PreparedStatement preparedStatement) -> {
            preparedStatement.setInt(1, screening.getAuditoriumID());
            preparedStatement.setInt(2, screening.getMovieID());
            preparedStatement.setDate(3, screening.getDate());
            preparedStatement.setTime(4, screening.getStartTime());
            preparedStatement.setTime(5, screening.getEndTime());
        };
        super.setStatementMapper(statementMapper);
    }

    @Override
    public Screening getScreeningByID(Integer id) {
        return get(MySQLConstants.Screening.SQL_GET_SCREENING_BY_ID, id);
    }

    @Override
    public Screening getScreeningByDateAndAuditoriumID(Date date, Integer id) {
        return get(MySQLConstants.Screening.SQL_GET_SCREENING_BY_DATE_AND_AUDITORIUM_ID, date, id);
    }

    @Override
    public Screening getScreeningByTicketID(Integer id) {
        return get(MySQLConstants.Screening.SQL_GET_SCREENING_BY_TICKET_ID, id);
    }

    @Override
    public List<Screening> getAllScreenings() {
        return getAll(MySQLConstants.Screening.SQL_GET_ALL_SCREENINGS);
    }

    @Override
    public List<Screening> getScreeningsByDate(Date date, Time time, String orderBy, String direction) {
        if (orderBy.equals("movie_name")) {
            return getAllBy(MySQLConstants.Screening.SQL_GET_SCREENINGS_BY_DATE_ORDER_BY_MOVIE_NAME.concat(" ").concat(direction), date, time);
        }

        return getAllBy(MySQLConstants.Screening.SQL_GET_SCREENINGS_BY_DATE.concat(orderBy).concat(" ").concat(direction), date, time);
    }

    @Override
    public List<Screening> getScreeningsByPagination(Integer missedScreenings, Integer numberScreenings) {
        return getAllBy(MySQLConstants.Screening.SQL_GET_SCREENINGS_BY_PAGINATION, missedScreenings, numberScreenings);
    }

    @Override
    public List<Screening> getScreeningsByDatesWithPagination(Integer missedScreenings, Integer numberScreenings) {
        Date currentDate = new Date(System.currentTimeMillis());
        return getAllBy(MySQLConstants.Screening.SQL_GET_SCREENINGS_BY_DATE_WITH_PAGINATION, currentDate.toString(), missedScreenings, numberScreenings);
    }

    @Override
    public boolean addScreening(Screening screening) {
        return add(screening, MySQLConstants.Screening.SQL_ADD_SCREENING);
    }

    @Override
    public boolean updateScreening(Screening screening) {
        return update(screening, MySQLConstants.Screening.SQL_UPDATE_SCREENING_BY_ID, 6, screening.getScreeningID());
    }

    @Override
    public boolean deleteScreeningByDate(Date date) {
        return delete(date, MySQLConstants.Screening.SQL_DELETE_SCREENINGS_BY_DATE);
    }

    @Override
    public boolean deleteScreeningByID(Integer id) {
        return delete(id, MySQLConstants.Screening.SQL_DELETE_SCREENING_BY_ID);
    }
}
