package com.epam.cinema.enity.validator;

import com.epam.cinema.enity.Screening;
import com.epam.cinema.enity.constraint.TimeIsAfter;
import org.apache.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.sql.Time;

public class TimeIsAfterValidator implements ConstraintValidator<TimeIsAfter, Object> {
    private static final Logger log = Logger.getLogger(TimeIsAfterValidator.class);
    private String[] fieldName;

    @Override
    public void initialize(TimeIsAfter constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Field startField = Screening.class.getDeclaredField(fieldName[0]);
            Field endField = Screening.class.getDeclaredField(fieldName[1]);

            startField.setAccessible(true);
            endField.setAccessible(true);

            Time startTime = (Time) startField.get(object);
            Time endTime = (Time) endField.get(object);
            return endTime.toLocalTime().isAfter(startTime.toLocalTime());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            log.error(e);
            return false;
        }
    }
}
