package com.dimpon.tutorals.lombok.builders;

/**
 * @author Dmitrii Ponomarev
 */

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Singular;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatherComplexBuilder<T> implements Transformer<T> {

    @Builder.Default
    final String filterParam = "e1";

    @Singular("elements")
    List<T> elements;

    @Singular("function")
    List<Function<T, T>> operations;


    @Override
    public T doTransformation() {
        return elements.stream()
                .peek(System.out::println)
                .filter(t -> t.toString().equals(filterParam))
                .peek(System.out::println)
                .map(t -> combineFunctions(operations).apply(t))
                .findAny().orElseThrow(() -> new NullPointerException("it's me"));
    }


    private Function<T, T> combineFunctions(List<Function<T, T>> functions) {
        return functions.stream().reduce(Function.identity(), Function::andThen);
    }

    /*private BiFunction<T,String, T> combineFunctions(List<BiFunction<T,String, T>> functions) {
        return functions.stream().reduce((t,p)->t, BiFunction::andThen);
    }*/
}
