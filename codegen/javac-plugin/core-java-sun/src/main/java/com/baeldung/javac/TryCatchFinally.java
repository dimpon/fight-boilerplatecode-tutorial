package com.baeldung.javac;

import java.lang.annotation.*;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target(LOCAL_VARIABLE)
public @interface TryCatchFinally {
    Class<? extends Throwable>[] value();

    Loguno[] elements() default {};


    Class<? extends Consumer<? extends Throwable>>[] catchcode() default {};

}



