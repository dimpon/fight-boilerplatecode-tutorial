package com.dimpon.tutorals.methodannprocessor;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start {

	public static void main(String[] args) {



		final AnnotationOperator.FunctionWithExceptions<HoundImpl, LevelOfBarking> operation = (h, a) -> {
			//throw new IllegalArgumentException("Bla bla bla");
			int level = a.level();
			h.bark(level);
		};

		Hound hound = AnnotationOperator.<HoundImpl, LevelOfBarking, Hound> builder()
				.annotationClass(LevelOfBarking.class)
				.interfaceClass(Hound.class)
				.original(new HoundImpl())
				.operation(operation)
				.build()
				.getProxy();


		try {
			hound.bark();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
