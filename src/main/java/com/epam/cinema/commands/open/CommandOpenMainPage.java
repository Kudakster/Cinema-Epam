package com.epam.cinema.commands.open;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.commands.RequestUtil;
import com.epam.cinema.enity.Movie;
import com.epam.cinema.enity.Screening;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Forward;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public class CommandOpenMainPage implements ICommand {
    private final String PAGE = "main";
    private final Integer NUMBER_DAYS = 7;
    private final Long CURRENT_DAY = System.currentTimeMillis();

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        String date = request.getParameter("date");
        Date dateFromRequest;
        Date currentDate = new Date(CURRENT_DAY);

        if (StringUtils.isEmpty(date) || date.equals(currentDate.toString())) {
            dateFromRequest = currentDate;
        } else {
            dateFromRequest = Date.valueOf(date);
        }

        Map<Movie, List<Screening>> screenings = ServiceFactory.getScreeningService().getGroupedMapScreeningByMovie(dateFromRequest);
        List<Date> dates = RequestUtil.getListOfDate(CURRENT_DAY, NUMBER_DAYS);

        request.setAttribute("dates", dates);
        request.setAttribute("screenings", screenings);
        return new Forward(PAGE);
    }
}
