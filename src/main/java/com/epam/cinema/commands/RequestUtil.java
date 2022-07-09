package com.epam.cinema.commands;

import com.epam.cinema.enity.Movie;
import com.epam.cinema.enity.Screening;
import com.epam.cinema.enity.User;
import com.epam.cinema.enity.enumeration.UserRole;
import com.epam.cinema.service.implementation.ImageUploadingService;
import com.epam.cinema.service.ServiceFactory;
import com.epam.cinema.servlets.ServiceServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

public class RequestUtil {
    private static final Logger log = Logger.getLogger(ServiceServlet.class);

    public Movie getMovieFromRequest(HttpServletRequest request) {
        String name = request.getParameter("movie-name");
        String actors = request.getParameter("actors");
        String direction = request.getParameter("direction");
        String genre = request.getParameter("genre");
        String country = request.getParameter("country");
        String trailerURL = request.getParameter("trailer-url");
        String imgURL = getImageUrl(request);
        String durationMin = request.getParameter("duration-min");
        String description = request.getParameter("description");
        Date releaseDate = parseStringToDate(request.getParameter("release-date"));

        Movie movie = new Movie();
        movie.setName(name);
        movie.setReleaseDate(releaseDate);
        movie.setActors(actors);
        movie.setDirection(direction);
        movie.setGenre(genre);
        movie.setCountry(country);
        movie.setTrailerURL(trailerURL);
        movie.setImgURL(imgURL);
        movie.setDurationMin(durationMin);
        movie.setDescription(description);
        return movie;
    }

    public User getUserFromRequest(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstname");
        String surName = request.getParameter("surname");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone-number");
        return new User(login, password, firstName, surName, email, phoneNumber, UserRole.USER);
    }

    public Screening getScreeningFromRequest(HttpServletRequest request) {
        Integer screeningID = parseStringToInteger(request.getParameter("screeningID"));
        String movieName = request.getParameter("movie-name");
        Integer movieID = ServiceFactory.getMovieService().findMovieByName(movieName).getId();
        String date = request.getParameter("date");
        Time startTime = parseStringToTime(request.getParameter("startTime"));
        Time endTime = parseStringToTime(request.getParameter("endTime"));

        Screening screening = new Screening();
        screening.setScreeningID(screeningID);
        screening.setMovieID(movieID);
        screening.setAuditoriumID(1);
        screening.setDate(parseStringToDate(date));
        screening.setStartTime(startTime);
        screening.setEndTime(endTime);
        return screening;
    }

    public static List<Date> getListOfDate(Long dateInMilSec, Integer numberOfDate) {
        long milsInOneDay = 86_400_000L;
        List<Date> dateList = new LinkedList<>();
        for (int i = 0; i < numberOfDate; i++) {
            Date date = new Date(dateInMilSec + milsInOneDay * i);
            dateList.add(date);
        }
        return dateList;
    }

    private Integer parseStringToInteger(String string) {
        return string != null ? Integer.valueOf(string) : null;
    }

    private Date parseStringToDate(String string) {
        return string != null ? Date.valueOf(string) : null;
    }

    private Time parseStringToTime(String string) {
        return string != null ? Time.valueOf(string.substring(0, 5).concat(":00")) : null;
    }

    private String getImageUrl(HttpServletRequest request) {
        try {
            Part part = request.getPart("image");
            Part[] parts = request.getParts().toArray(new Part[0]);
            if (part.getSize() > 0) {
                return ImageUploadingService.uploadImage(part, parts);
            }
            throw new NullPointerException("Part is equal to 0");
        } catch (IOException | ServletException | NullPointerException e) {
            log.info(e.getMessage());
            return request.getParameter("image-src");
        }
    }
}
