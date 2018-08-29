package com.dimpon.tutorals.lombok.ulility;

import lombok.experimental.FieldNameConstants;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Dmitrii Ponomarev
 */
@UtilityClass
public class MyLovelyUtils {

    public <T> Collection<T> applyFunctionOnOnCollection(Collection<T> in, Function<T, T> function) {
        return in.stream().map(function).collect(Collectors.toCollection(HashSet::new));
    }

    public String sayHello() {
        return sayGoodbye();
    }

    private String sayGoodbye() {
        return "goodbye";
    }

}
