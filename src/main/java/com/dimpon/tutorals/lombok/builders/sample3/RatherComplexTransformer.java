package com.dimpon.tutorals.lombok.builders.sample3;

/**
 * @author Dmitrii Ponomarev
 */

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Singular;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatherComplexTransformer<T> implements Transformer<T> {

	@Singular("elements")
	List<T> elements;

	@Singular("function")
	List<Function<T, T>> operations;

	@Override
	public Stream<T> doTransformation() {

		return elements.stream()
				.peek(e -> log.info(e.toString()))
				.map(t -> combineFunctions(operations).apply(t));
	}

	private Function<T, T> combineFunctions(List<Function<T, T>> functions) {
		return functions.stream().reduce(Function.identity(), Function::andThen);
	}
}





@Slf4j
class Starter {
	public static void main(String[] args) {

		Transformer<String> comBuilder = RatherComplexTransformer.<String> builder()

				.elements("e1")
				.elements("e2")
				.elements("e3")
				.elements("e4")
				.function(s -> s + "_A")
				.function(s -> s + "_B")
				.function(s -> s + "_C")
				.build();

		Stream<String> s = comBuilder.doTransformation();

		log.info("r=" + s.collect(Collectors.joining(" - ")));
	}
}
