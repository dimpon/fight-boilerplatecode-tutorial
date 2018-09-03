package com.dimpon.tutorals.validation.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = AutoAndOwnerValidator.class)
@Target({ METHOD, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface AutoAndOwner {
    String message() default
            "If you drank you can drive only broken car :))";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
