package com.epam.cinema.enity.constraint;

import com.epam.cinema.enity.validator.TimeIsAfterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = TimeIsAfterValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface TimeIsAfter {
    String message() default "screening.timeNotValid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] fieldName();
}
