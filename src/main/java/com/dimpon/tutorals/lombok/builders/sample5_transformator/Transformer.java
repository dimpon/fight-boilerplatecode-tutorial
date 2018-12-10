package com.dimpon.tutorals.lombok.builders.sample5_transformator;

public interface Transformer<S, R> {
	R transform(S source);
}
