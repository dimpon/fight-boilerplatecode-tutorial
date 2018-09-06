package com.dimpon.tutorals.commands;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Repeatable(Command.$List.class)
@Target(value = ElementType.TYPE)
@Retention(RUNTIME)
public @interface Command {
    Class<? extends CommandGranular> value();
    CommandElement[] elements() default {};



    @Deprecated
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    @interface $List {
        Command[] value();
    }
}
