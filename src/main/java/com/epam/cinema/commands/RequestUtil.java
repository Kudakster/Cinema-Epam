package com.epam.cinema.commands;

import com.epam.cinema.enity.Movie;
import com.epam.cinema.enity.Screening;
import com.epam.cinema.service.implementation.ImageUploadingService;
import com.epam.cinema.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class RequestUtil {
    public static Movie getMovieFromRequest(HttpServletRequest request) {
        String name = request.getParameter("movie-name");
        String actors = request.getParameter("actors");
        String direction = request.getParameter("direction");
        String genre = request.getParameter("genre");
        String country = request.getParameter("country");
        String trailerURL = request.getParameter("trailer-url");
        String imgURL = ImageUploadingService.uploadImage(request, "image");
        String durationMin = request.getParameter("duration-min");
        String description = request.getParameter("description");
        java.util.Date date = null;
        Date releaseDate = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = simpleDateFormat.parse(request.getParameter("release-date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            releaseDate = new Date(date.getTime());
        }

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

    public static Screening getScreeningFromRequest(HttpServletRequest request) {
        String screeningID = request.getParameter("screeningID");
        String movieName = request.getParameter("movie-name");
        String date = request.getParameter("date");
        Time startTime = Time.valueOf(request.getParameter("startTime").substring(0, 5).concat(":00"));
        Time endTime = Time.valueOf(request.getParameter("endTime").substring(0, 5).concat(":00"));

        Screening screening = new Screening();
        screening.setScreeningID(parseStringToInteger(screeningID));
        screening.setMovieID(ServiceFactory.getMovieService().findMovieByName(movieName).getId());
        screening.setAuditoriumID(1);
        screening.setDate(Date.valueOf(date));
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

    private static Integer parseStringToInteger(String string) {
        return string != null ? Integer.valueOf(string) : null;
    }



}
