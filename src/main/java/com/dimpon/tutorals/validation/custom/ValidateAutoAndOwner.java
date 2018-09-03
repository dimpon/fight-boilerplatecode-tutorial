package com.dimpon.tutorals.validation.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, TYPE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { ValidateAutoAndOwnerValidator.class })
public @interface ValidateAutoAndOwner {

	String message() default "Owner and Auto";

    Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value() default "";

}
