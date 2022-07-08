package com.epam.cinema.commands;

import com.epam.cinema.enity.Entity;
import com.epam.cinema.service.ServiceFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;
import java.sql.Time;
import java.util.Objects;
import java.util.Set;

public class Validation {
    public static String validate(Entity entity) {
        ValidatorFactory factory = javax.validation.Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        String result = "";

        Set<ConstraintViolation<Entity>> violations = validator.validate(entity);
        for (ConstraintViolation<Entity> cons : violations) {
            result = cons.getMessage();
        }

        return result;
    }

    public static boolean isMovieExist(String name) {
        return ServiceFactory.getMovieService().findMovieByName(name) != null;
    }

    public static boolean isDurationCorrectly(Integer id, Time startTime, Time endTime) {
        String min = ServiceFactory.getMovieService().findMovieById(id).getDurationMin();

        if (Objects.equals(min, "")) {
            return false;
        }

        int durationMin = Integer.parseInt(min);
        return endTime.getTime() - startTime.getTime() >= durationMin * 60000L;
    }
}
