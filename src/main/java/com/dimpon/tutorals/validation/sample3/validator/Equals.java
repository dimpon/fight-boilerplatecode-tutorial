package com.dimpon.tutorals.validation.sample3.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD,PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { EqualsValidator.class })
public @interface Equals {

	String message() default "Must be equal to {value}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value();

}
