package com.dimpon.tutorals.lombok.builders.sample5_transformator;

import lombok.Builder;
import lombok.Singular;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author Dmitrii Ponomarev
 */
@Builder
public class TransformatorImpl<S, R> implements Transformator<S,R> {

	@Builder.Default
	private final Supplier<R> emptyResult = () -> {
		throw new IllegalStateException("provide supplier for result object");
	};

	@Singular
	// @Builder.Default
	private List<BiFunction<R, S, R>> operations = Collections.emptyList();

	@Override
	public R transform(S source) {
		return reduce(operations, source).apply(emptyResult.get(), source);
	}

	private BiFunction<R, S, R> reduce(List<BiFunction<R, S, R>> functions, S source) {
		return functions.stream()
				.reduce((t, r) -> t,
						(b, a) -> b.andThen(t -> a.apply(t, source)));
	}
}
