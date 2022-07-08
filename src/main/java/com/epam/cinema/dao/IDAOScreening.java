package com.epam.cinema.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface IDAOScreening<Screening> {
    Screening getScreeningByID(Integer id);

    Screening getScreeningByTicketID(Integer id);

    Screening getScreeningByDateAndAuditoriumID(Date date, Integer id);

    List<Screening> getAllScreenings();

    List<Screening> getScreeningsByPagination(Integer missedScreenings, Integer numberScreenings);

    List<Screening> getScreeningsByDate(Date date, Time time, String orderBy, String direction);

    List<Screening> getScreeningsByDatesWithPagination(Integer missedScreenings, Integer numberScreenings);

    boolean addScreening(Screening screening);

    boolean updateScreening(Screening screening);

    boolean deleteScreeningByDate(Date date);

    boolean deleteScreeningByID(Integer id);

}
