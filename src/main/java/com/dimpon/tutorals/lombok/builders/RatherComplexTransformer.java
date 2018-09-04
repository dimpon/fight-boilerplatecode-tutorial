package com.dimpon.tutorals.lombok.builders;

/**
 * @author Dmitrii Ponomarev
 */

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Singular;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatherComplexTransformer<T> implements Transformer<T> {

	@Builder.Default
	final String filterParam = "e1";

	@Singular("elements")
	List<T> elements;

	@Singular("function")
	List<Function<T, T>> operations;

	@Override
	public T doTransformation() {

		log.info("point 1");

		log.info("point 2");

		log.info("point 3");

		return elements.stream()
				.peek(e->log.info(e.toString()))
				.filter(t -> t.toString().equals(filterParam))
				.peek(e->log.info(e.toString()))
				.map(t -> combineFunctions(operations).apply(t))
				.findAny().orElseThrow(() -> new NullPointerException("it's me"));
	}

	private Function<T, T> combineFunctions(List<Function<T, T>> functions) {
		return functions.stream().reduce(Function.identity(), Function::andThen);
	}

}
