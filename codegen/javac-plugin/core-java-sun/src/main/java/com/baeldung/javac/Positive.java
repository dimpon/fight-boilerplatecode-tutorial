package com.baeldung.javac;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.PARAMETER, ElementType.TYPE, ElementType.LOCAL_VARIABLE})
public @interface Positive {
}
