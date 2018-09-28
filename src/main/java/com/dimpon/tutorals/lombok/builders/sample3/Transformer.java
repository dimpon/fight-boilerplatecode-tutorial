package com.dimpon.tutorals.lombok.builders.sample3;

import java.util.stream.Stream;

@FunctionalInterface
public interface Transformer<T> {
    Stream<T> doTransformation();
}
