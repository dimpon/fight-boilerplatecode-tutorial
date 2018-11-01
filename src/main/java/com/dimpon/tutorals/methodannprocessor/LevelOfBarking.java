package com.dimpon.tutorals.methodannprocessor;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LevelOfBarking {
    int level() default 0;
}
