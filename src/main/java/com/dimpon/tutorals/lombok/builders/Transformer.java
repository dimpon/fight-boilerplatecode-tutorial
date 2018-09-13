package com.dimpon.tutorals.lombok.builders;

import java.util.stream.Stream;

@FunctionalInterface
public interface Transformer<T> {
    Stream<T> doTransformation();
}
