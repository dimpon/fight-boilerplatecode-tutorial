package com.dimpon.tutorals.validation.sample8.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ValidateNObjectsValidator.class)
@Target({ METHOD, CONSTRUCTOR })
@Retention(RUNTIME)
@Documented
public @interface ValidateNObjects {
    String message() default
            "If you drank you can drive only broken car :))";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends ValidateNObjectsValidator.ValidateNObjectsCommand> command();

    Class<?>[] value();


}
