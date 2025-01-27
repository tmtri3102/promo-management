package com.promo.validator;

import com.promo.model.Promo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class EndDateValidator  implements ConstraintValidator<ValidEndDate, Promo> {
    @Override
    public boolean isValid(Promo promo, ConstraintValidatorContext context) {
        if (promo == null) {
            return true;
        }

        return promo.getEndDate().isAfter(promo.getStartDate());
    }
}
