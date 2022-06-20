package com.epam.cinema.commands;

import com.epam.cinema.config.Configuration;
import com.epam.cinema.enity.Auditorium;
import com.epam.cinema.enity.Entity;
import com.epam.cinema.service.ServiceFactory;

import javax.servlet.http.Cookie;
import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.Objects;
import java.util.Set;

public class Validation {
    public static Cookie validate(Entity entity) {
        ValidatorFactory factory = javax.validation.Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        Cookie cookie = new Cookie("error", "");
        String result = "";

        Set<ConstraintViolation<Entity>> violations = validator.validate(entity);
        for (ConstraintViolation<Entity> cons : violations) {
            result = cons.getMessage();
        }

        if (!Objects.equals(result, "")) {
            cookie.setValue(URLEncoder.encode((Configuration.getInstance().getError(result)), StandardCharsets.UTF_8));
        }

        return cookie;
    }

    public static String isLoginAvailable(String login) {
        if (ServiceFactory.getUserService().findUserByLogin(login) != null) {
            return "Sorry, " + login + " login is already taken";
        }
        return null;
    }

    public static Integer validAndGetAuditoriumID(String auditoriumName) {
        if (auditoriumName == null) {
            return null;
        }

        Auditorium auditorium = ServiceFactory.getAuditoriumService().findAuditoriumByName(auditoriumName);
        if (auditorium != null) {
            return auditorium.getAuditoriumID();
        }

        return null;
    }

    public static boolean isMovieExist(String name) {
        return ServiceFactory.getMovieService().findMovieByName(name) != null;
    }

    public static boolean isDurationCorrectly(String name, String startTime, String endTime) {
        String min = ServiceFactory.getMovieService().findMovieByName(name).getDurationMin();

        if (Objects.equals(min, "")) {
            return false;
        }

        Integer durationMin = Integer.valueOf(min);
        Time start = Time.valueOf(startTime.substring(0, 5).concat(":00"));
        Time end = Time.valueOf(endTime.substring(0, 5).concat(":00"));
        return end.getTime() - start.getTime() >= durationMin * 60000;
    }
}