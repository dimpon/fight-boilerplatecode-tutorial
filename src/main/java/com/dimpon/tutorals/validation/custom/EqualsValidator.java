package com.dimpon.tutorals.validation.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author Dmitrii Ponomarev
 */
public class EqualsValidator implements ConstraintValidator<Equals, String> {

    protected String value;

    @Override
    public void initialize(Equals ageValue) {
        this.value = ageValue.value();
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        return value.equals(date);
    }
}
