package com.baeldung.javac;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Repeatable(Loguno.List.class)
@Retention(RetentionPolicy.CLASS)
@Target({METHOD, TYPE, LOCAL_VARIABLE, PARAMETER, TYPE_PARAMETER,TYPE_USE})
public @interface Loguno {

    String[] value() default "";

    Loglevel level() default Loglevel.INFO;

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({METHOD, TYPE, LOCAL_VARIABLE, PARAMETER, TYPE_PARAMETER,TYPE_USE})
    @interface List {
        Loguno[] value();
    }

    public enum Loglevel{
        INFO,
        DEBUG,
        ERROR
    }
}



