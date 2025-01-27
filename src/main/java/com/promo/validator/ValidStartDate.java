package com.promo.validator;

import java.lang.annotation.*;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = StartDateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStartDate {
    String message() default "Start Date must be after today";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
