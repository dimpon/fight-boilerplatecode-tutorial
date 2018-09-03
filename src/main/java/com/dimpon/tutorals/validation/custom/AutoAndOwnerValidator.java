package com.dimpon.tutorals.validation.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

/**
 * @author Dmitrii Ponomarev
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class AutoAndOwnerValidator implements ConstraintValidator<AutoAndOwner, Object[]> {

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {


        return false;
    }
}
