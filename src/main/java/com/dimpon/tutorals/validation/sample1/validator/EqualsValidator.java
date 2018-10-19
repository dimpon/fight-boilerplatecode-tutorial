package com.dimpon.tutorals.validation.sample1.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
