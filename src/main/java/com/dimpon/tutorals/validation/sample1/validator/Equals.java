package com.dimpon.tutorals.validation.sample1.validator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { EqualsValidator.class })
public @interface Equals {

	String message() default "Must be greater than {value}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value();

}
