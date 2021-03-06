package com.dimpon.tutorals.lombok.ulility;

import org.slf4j.Logger;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Dmitrii Ponomarev
 */
public class Start {

    public static void main(String[] args) {


        Function<Class<?>, Logger> logger = LambdaHolder.logger;

        Logger log1 = logger.apply(Start.class);

        Logger log2 = LambdaHolder.logger.apply(Start.class);





        String fieldHello = MyLovelyConstants.FIELD_HELLO;

        String s = MyLovelyUtils.sayHello();

        Collection<String> result =  MyLovelyUtils.applyFunctionOnCollection(Stream.of(1,2,3).collect(Collectors.toList()), Object::toString);




    }
}
