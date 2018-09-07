package com.dimpon.tutorals.validation.quickfix.validators;

import com.dimpon.tutorals.validation.custom.Equals;
import com.dimpon.tutorals.validation.quickfix.NewOrderSingleDelegate;
import com.dimpon.tutorals.validation.quickfix.annotations.IsLimitAndPriceIsSet;
import lombok.SneakyThrows;
import quickfix.FieldNotFound;
import quickfix.field.OrdType;
import quickfix.field.Price;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Dmitrii Ponomarev
 */
public class IsLimitAndPriceIsSetValidator implements ConstraintValidator<IsLimitAndPriceIsSet, NewOrderSingleDelegate>  {

    @Override
    @SneakyThrows(FieldNotFound.class)
    public boolean isValid(NewOrderSingleDelegate value, ConstraintValidatorContext context) {

        char orderType = value.getOrder().getOrdType().getValue();

        if (orderType == OrdType.LIMIT) {
            return value.getOrder().isSet(new Price());
        }

        return true;
    }
}
