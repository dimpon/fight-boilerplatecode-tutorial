package com.dimpon.tutorals.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({ TYPE, FIELD, CONSTRUCTOR, METHOD, PARAMETER, ANNOTATION_TYPE, LOCAL_VARIABLE, PACKAGE, TYPE_PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface Bird {
	String[] value() default {};
}
