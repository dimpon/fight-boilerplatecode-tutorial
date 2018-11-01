package com.dimpon.tutorals.validation;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Dmitrii Ponomarev
 */
@UtilityClass
@Slf4j
public class PrintUtils {

	public <T> void print(Set<ConstraintViolation<T>> violations) {
		log.info("\n\n" +
				violations.stream()
						.map(v -> v.getMessage() + ": " + v.getInvalidValue())
						.collect(Collectors.joining("\n")));
	}
}
