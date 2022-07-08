package com.epam.cinema.service.implementation;

import com.epam.cinema.dao.implementation.DAOScreeningImpl;
import com.epam.cinema.enity.Movie;
import com.epam.cinema.enity.Screening;
import com.epam.cinema.service.IScreeningService;
import com.epam.cinema.service.ServiceFactory;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class ScreeningServiceImpl implements IScreeningService {
    private DAOScreeningImpl daoScreening;
    private static ScreeningServiceImpl instance = null;

    public ScreeningServiceImpl() {
        daoScreening = new DAOScreeningImpl();
    }

    @Override
    public Screening findScreeningById(Integer id) {
        return daoScreening.getScreeningByID(id);
    }

    @Override
    public Screening findScreeningByDateAndAuditoriumID(Date date, Integer id) {
        return daoScreening.getScreeningByDateAndAuditoriumID(date, id);
    }

    @Override
    public Screening findScreeningByTicketID(Integer id) {
        return daoScreening.getScreeningByTicketID(id);
    }

    @Override
    public List<Screening> findAllScreenings() {
        return daoScreening.getAllScreenings();
    }

    @Override
    public List<Screening> findScreeningsByDate(Date date, Time time, String orderBy, String direction) {
        return daoScreening.getScreeningsByDate(date, time, orderBy, direction);
    }

    @Override
    public List<Screening> findScreeningsByPagination(Integer missedScreenings, Integer numberScreenings) {
        return daoScreening.getScreeningsByPagination(missedScreenings, numberScreenings);
    }

    @Override
    public List<Screening> findScreeningsByDatesWithPagination(Integer missedScreenings, Integer numberScreenings) {
        return daoScreening.getScreeningsByDatesWithPagination(missedScreenings, numberScreenings);
    }

    @Override
    public boolean addScreening(Screening screening) {
        if (screening.getStartTime().getTime() > screening.getEndTime().getTime()) {
            return false;
        }

        List<Screening> screenings = daoScreening.getScreeningsByDate(screening.getDate(), screening.getStartTime(), "screening_start_time", "ASC");

        boolean result = true;
        if (screenings != null) {
            result = screenings.stream().allMatch(sc -> isTimeAvailable(screening, sc.getStartTime(), sc.getEndTime()));
        }

        return result && daoScreening.addScreening(screening);
    }

    @Override
    public boolean updateScreening(Screening screening) {
        List<Screening> screenings = daoScreening.getScreeningsByDate(screening.getDate(), new Time(0), "screening_start_time", "ASC");
        boolean result = false;
        if (screenings != null) {
            screenings.removeIf(sc -> sc.getScreeningID().equals(screening.getScreeningID()));
            result = screenings.stream().allMatch(sc -> isTimeAvailable(screening, sc.getStartTime(), sc.getEndTime()));
        }
        return result && daoScreening.updateScreening(screening);
    }

    @Override
    public boolean deleteScreeningByID(Integer id) {
        return daoScreening.deleteScreeningByID(id);
    }

    @Override
    public boolean deleteScreeningsByDate(Date date) {
        return daoScreening.deleteScreeningByDate(date);
    }

    public Map<Date, List<Screening>> getGroupedMapScreeningByDateWithPagination(Integer missedDays, Integer numberOfDays) {
        List<Screening> screeningList = findScreeningsByDatesWithPagination(missedDays, numberOfDays);

        if (screeningList == null) {
            return new TreeMap<>();
        }

        return new TreeMap<>(screeningList
                .stream()
                .collect(groupingBy(Screening::getDate)));
    }

    public Map<Movie, List<Screening>> getGroupedMapScreeningByMovie(Date date, String orderBy, String direction) {
        MovieServiceImpl movieService = ServiceFactory.getMovieService();
        List<Screening> screeningList = findScreeningsByDate(date, new Time(date.getTime()), orderBy, direction);

        return screeningList != null ? screeningList.stream().collect(groupingBy(screening -> movieService.findMovieById(screening.getMovieID()), LinkedHashMap::new, toList())) : null;
    }

    private boolean isTimeAvailable(Screening screening, Time startTime, Time endTime) {
        return (screening.getStartTime().getTime() < startTime.getTime() &&
                screening.getEndTime().getTime() < startTime.getTime()) ||
                (screening.getStartTime().getTime() > endTime.getTime() &&
                        screening.getEndTime().getTime() > endTime.getTime());
    }

    public static ScreeningServiceImpl getInstance() {
        if (instance == null) {
            instance = new ScreeningServiceImpl();
        }
        return instance;
    }

    public static void setInstance(ScreeningServiceImpl instance) {
        ScreeningServiceImpl.instance = instance;
    }
}
