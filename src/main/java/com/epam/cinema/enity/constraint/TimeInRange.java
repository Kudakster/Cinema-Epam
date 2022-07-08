package com.epam.cinema.enity.constraint;

import com.epam.cinema.enity.validator.TimeInRangeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = TimeInRangeValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface TimeInRange {
    String message() default "screening.startTimeNotValid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String min();

    String max();
}
