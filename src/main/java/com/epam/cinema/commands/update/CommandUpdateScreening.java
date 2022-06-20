package com.epam.cinema.commands.update;

import com.epam.cinema.commands.ICommand;
import com.epam.cinema.commands.RequestUtil;
import com.epam.cinema.commands.Validation;
import com.epam.cinema.enity.Screening;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.Executor;
import com.epam.cinema.servlets.Redirect;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandUpdateScreening implements ICommand {
    private final String COMMAND = "admin";
    private final String ERROR_COMMAND = "admin";
    private final String ERROR_KEY = "screening.update.error";
    private final String ERROR_KEY_DURATION = "screening.update.duration";
    private final String ERROR_KEY_TIME_NOT_CORRECT = "screening.update.time";

    @Override
    public Executor execute(HttpServletRequest request, HttpServletResponse response) {
        if (!Validation.isMovieExist(request.getParameter("movie-name"))) {
            return new Redirect(false, COMMAND, ERROR_COMMAND, ERROR_KEY);
        }

        Screening screening = RequestUtil.getScreeningFromRequest(request);

        if (screening.getStartTime().getTime() > screening.getEndTime().getTime()) {
            return new Redirect(false, COMMAND, ERROR_COMMAND, ERROR_KEY_TIME_NOT_CORRECT);
        }

        if (!Validation.isDurationCorrectly(request.getParameter("movie-name"), request.getParameter("startTime"), request.getParameter("endTime"))) {
            return new Redirect(false, COMMAND, ERROR_COMMAND, ERROR_KEY_DURATION);
        }

        if (screening.getScreeningID() == null) {
          return new Redirect(ServiceFactory.getScreeningService().addScreening(screening), COMMAND, ERROR_COMMAND, ERROR_KEY);
        }

        return new Redirect(ServiceFactory.getScreeningService().updateScreening(screening), COMMAND, ERROR_COMMAND, ERROR_KEY);
    }
}