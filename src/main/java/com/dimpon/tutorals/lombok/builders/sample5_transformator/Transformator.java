package com.dimpon.tutorals.lombok.builders.sample5_transformator;

public interface Transformator<S, R> {
	R transform(S source);
}
