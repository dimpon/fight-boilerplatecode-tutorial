package com.dimpon.tutorals.validation.custom;

import com.dimpon.tutorals.validation.Start;
import com.dimpon.tutorals.validation.dto.Auto;
import com.dimpon.tutorals.validation.dto.Owner;
import com.dimpon.tutorals.validation.dto.OwnerAndAutoPair;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.AbstractMap;

/**
 * @author Dmitrii Ponomarev
 */
public class ValidateAutoAndOwnerValidator implements ConstraintValidator<ValidateAutoAndOwner, OwnerAndAutoPair> {

	@Override
	public void initialize(ValidateAutoAndOwner val) {

	}

	@Override
	public boolean isValid(OwnerAndAutoPair value, ConstraintValidatorContext context) {

		if (value.getOwner().drunk() && !value.getAuto().broken())
			return false;

		return true;
	}

}
