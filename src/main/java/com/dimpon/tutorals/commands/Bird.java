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

	Xoxox xxx() default Xoxox.X1;

	enum Xoxox {
		X1, X2;
	}


	static class $L{public static void INFO(String ... arg){}}


	class ${
		public static void INFO(String ... arg){}
	}
}
