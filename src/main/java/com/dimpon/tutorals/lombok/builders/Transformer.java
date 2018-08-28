package com.dimpon.tutorals.lombok.builders;

@FunctionalInterface
public interface Transformer<T> {
    T doTransformation();
}
