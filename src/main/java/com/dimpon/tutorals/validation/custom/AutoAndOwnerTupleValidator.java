package com.dimpon.tutorals.validation.custom;

import com.dimpon.tutorals.validation.dto.Auto;
import com.dimpon.tutorals.validation.dto.Owner;
import com.dimpon.tutorals.validation.dto.OwnerAndAutoPair;
import org.apache.commons.lang3.tuple.Pair;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Dmitrii Ponomarev
 */
public class AutoAndOwnerTupleValidator  implements ConstraintValidator<AutoAndOwnerTuple, Pair<Auto, Owner>> {

    @Override
    public void initialize(AutoAndOwnerTuple val) {

    }

    @Override
    public boolean isValid(Pair<Auto, Owner> value, ConstraintValidatorContext context) {

        if (value.getRight().drunk() && !value.getLeft().broken())
            return false;

        return true;
    }
}
