package com.dimpon.test;

import com.baeldung.javac.Positive;

/**
 * @author Dmitrii Ponomarev
 */
@Positive
public class Arbuz {



    public static String vax = "xxx";

    public int plus(@Positive int a, @Positive int b) {
        return a + b;
    }


}
