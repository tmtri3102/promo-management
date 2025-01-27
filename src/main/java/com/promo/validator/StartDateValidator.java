package com.promo.validator;

import com.promo.model.Promo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class StartDateValidator implements ConstraintValidator<ValidStartDate, Promo> {
    @Override
    public boolean isValid(Promo promo, ConstraintValidatorContext context) {
        if (promo == null) {
            return true;
        }

        LocalDate now = LocalDate.now();

        if (promo.getStartDate().isBefore(now)) {
            return false;
        }

        return true;
    }
}
