package com.dimpon.tutorals.validation.sample6.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = AutoAndOwnerTupleValidator.class)
@Target({ PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface AutoAndOwnerTuple {
    String message() default
            "If you drank you can drive only broken car :))";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
