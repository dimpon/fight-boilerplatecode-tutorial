package com.dimpon.tutorals.lombok.builders.sample5_transformator;

import lombok.Builder;
import lombok.Singular;

import java.util.*;
import java.util.function.BiFunction;

/**
 * @author Dmitrii Ponomarev
 */
@Builder
public class Transformator<T, R> {

	private final R source;

	// @Singular
	// private List<BiFunction<T, R, T>> operations;

	//@Singular
	@Builder.Default
	private Map<TransformationSet, List<BiFunction<T, R, T>>> operations = new EnumMap<>(TransformationSet.class);

	public Transformator<T, R> annFunction(final TransformationSet setType, final BiFunction<T, R, T> function) {
		final List<BiFunction<T, R, T>> oneSet = operations.getOrDefault(setType, new ArrayList<>());
		oneSet.add(function);
		operations.putIfAbsent(setType, oneSet);
		return this;
	}

	public T transform(final TransformationSet setType, final T empty) {
		return reduce(operations.getOrDefault(setType, Collections.emptyList())).apply(empty, source);
	}

	private BiFunction<T, R, T> reduce(List<BiFunction<T, R, T>> functions) {
		return functions.stream()
				.reduce((t, r) -> t,
						(b, a) -> b.andThen(t -> a.apply(t, source)));
	}

	public enum TransformationSet {
		SET1, SET2, SET3;
	}
}
