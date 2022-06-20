package com.epam.cinema.commands.open;

import com.epam.cinema.commands.CookieUtil;
import com.epam.cinema.commands.ICommand;
import com.epam.cinema.enity.Movie;
import com.epam.cinema.enity.Screening;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class CommandOpenUpdateSchedule implements ICommand {
    private final String PAGE = "updateSchedule";
    private final String ERROR_PAGE = "admin";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        Integer missedScreenings = 0;
        Integer numberScreenings = 7;

        Map<Date, List<Screening>> screeningMap = ServiceFactory.getScreeningService()
                .getGroupedMapScreeningByDateWithPagination(missedScreenings, numberScreenings);

        Map<Integer, String> movieMap = null;

        if (screeningMap != null) {
            addAllDate(screeningMap);

            movieMap = screeningMap.values()
                    .stream()
                    .flatMap(Collection::stream)
                    .map(Screening::getMovieID)
                    .distinct()
                    .map(i -> ServiceFactory.getMovieService().findMovieById(i))
                    .collect(Collectors.toMap(Movie::getId, Movie::getName));
        }

        request.setAttribute("movieMap", movieMap);
        request.setAttribute("screeningMap", screeningMap);
        return new Forward(screeningMap != null, PAGE, ERROR_PAGE);
    }

    private void addAllDate(Map<Date, List<Screening>> map) {
        int dateNumber = 7;
        long milsInOneDay = 86_400_000L;
        for (int i = 0; i < dateNumber; i++) {
            Date date = new Date(System.currentTimeMillis() + milsInOneDay * i);
            if (map.keySet()
                    .stream()
                    .noneMatch(key -> key.toString().equals(date.toString()))) {
                map.put(date, new ArrayList<>());
            }
        }
    }
}
