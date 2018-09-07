package com.dimpon.tutorals.validation.quickfix.annotations;

import com.dimpon.tutorals.validation.custom.EqualsValidator;
import com.dimpon.tutorals.validation.quickfix.validators.IsLimitAndPriceIsSetValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { IsLimitAndPriceIsSetValidator.class })
public @interface IsLimitAndPriceIsSet {

    String message() default "Order must have order type LIMIT and have Price";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value() default "";
}
