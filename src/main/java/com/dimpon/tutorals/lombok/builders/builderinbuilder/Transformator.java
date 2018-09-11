package com.dimpon.tutorals.lombok.builders.builderinbuilder;

import lombok.Builder;
import lombok.Singular;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author Dmitrii Ponomarev
 */
@Builder
public class Transformator<T, R> {

	final R source;

	@Singular
	private List<BiFunction<T, R, T>> operations;

	public T transform(final T empty) {
	    return reduce(operations).apply(empty,source);
	}

	private BiFunction<T, R, T> reduce(List<BiFunction<T, R, T>> functions) {
		return functions.stream()
				.reduce((t, r) -> t,
						(b, a) -> b.andThen(t -> a.apply(t, source)));
	}



}
