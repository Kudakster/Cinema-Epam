package com.epam.cinema.service;

import com.epam.cinema.enity.Screening;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface IScreeningService {
    Screening findScreeningById(Integer id);

    Screening findScreeningByTicketID(Integer id);

    Screening findScreeningByDateAndAuditoriumID(Date date, Integer id);

    List<Screening> findAllScreenings();

    List<Screening> findScreeningsByDate(Date date, Time time, String orderBy, String direction);

    List<Screening> findScreeningsByPagination(Integer missedScreenings, Integer numberScreenings);

    List<Screening> findScreeningsByDatesWithPagination(Integer missedScreenings, Integer numberScreenings);

    boolean addScreening(Screening screening);

    boolean updateScreening(Screening screening);

    boolean deleteScreeningByID(Integer id);

    boolean deleteScreeningsByDate(Date date);
}
