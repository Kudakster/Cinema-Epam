package com.epam.cinema.enity.validator;

import com.epam.cinema.enity.constraint.TimeInRange;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Time;
import java.time.LocalTime;

public class TimeInRangeValidator implements ConstraintValidator<TimeInRange, Time> {
    private LocalTime minTime;
    private LocalTime maxTime;

    @Override
    public void initialize(TimeInRange constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        minTime = LocalTime.parse(constraintAnnotation.min());
        maxTime = LocalTime.parse(constraintAnnotation.max());
    }

    @Override
    public boolean isValid(Time value, ConstraintValidatorContext constraintValidatorContext) {
        LocalTime valueLocalTIme = value.toLocalTime();

        return (valueLocalTIme.isAfter(minTime) || valueLocalTIme.equals(minTime))
                && (valueLocalTIme.isBefore(maxTime) || valueLocalTIme.equals(maxTime));
    }
}
