package com.dimpon.test;

import com.baeldung.javac.Loguno;
import com.baeldung.javac.Loguno.Loglevel;
import com.baeldung.javac.TryCatchFinally;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.baeldung.javac.Loguno.Loglevel.*;

/**
 * @author Dmitrii Ponomarev
 */

@Loguno(level = DEBUG)

public class Cumbersome<@Loguno K extends Comparable<K>> {

    Consumer<String> a = s ->{};



    @Loguno("calling doit method name:{} age:{}")
    public void doit(@Loguno String name, @Loguno int age) throws @Loguno IllegalAccessError {

            @Loguno
            String s = null;
            String s1 = null;
            String s2 = null;


            List<@Loguno String> r = Stream.of("", "").collect(Collectors.toCollection(ArrayList::new));

            List<@Loguno ? extends @Loguno Comparable> list;


            Object o = new @Loguno Object();


            String s3 = (@Loguno String) o;

            new <String>@Loguno Cumbersome();

            @TryCatchFinally(NumberFormatException.class)
            int i = Integer.parseInt("2324");

            try {
                s.charAt(1);
            } catch (@Loguno({"msg {} {} {}","s|s1|s2"}) NullPointerException e) {
            }



    }
}
