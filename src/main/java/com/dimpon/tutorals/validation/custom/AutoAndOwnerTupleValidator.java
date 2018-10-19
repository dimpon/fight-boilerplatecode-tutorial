package com.dimpon.tutorals.validation.custom;

import com.dimpon.tutorals.validation.sample1.Auto;
import com.dimpon.tutorals.validation.sample1.Owner;
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
