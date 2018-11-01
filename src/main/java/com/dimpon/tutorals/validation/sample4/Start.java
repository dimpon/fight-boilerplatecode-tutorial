package com.dimpon.tutorals.validation.sample4;

import com.dimpon.tutorals.validation.PrintUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import javax.validation.groups.Default;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author Dmitrii Ponomarev
 */

@Slf4j
public class Start {

	@SneakyThrows
	public static void main(String[] args) {
		constructor();
		params();
		method();
	}

	@SneakyThrows
	private static void constructor() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		ExecutableValidator executableValidator = factory.getValidator().forExecutables();

		Set<ConstraintViolation<Producer>> constraintViolations = executableValidator.validateConstructorParameters(Producer.class.getConstructor(String.class),
				new String[] { "s33" }, Default.class);

		PrintUtils.print(constraintViolations);
	}

	@SneakyThrows
	private static void params() {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		ExecutableValidator executableValidator = factory.getValidator().forExecutables();

		Producer p = new Producer("s");
		Method method = Producer.class.getDeclaredMethod("getName", int.class);
		Object[] params = { 3 };

		Set<ConstraintViolation<Producer>> constraintViolationsM = executableValidator
				.validateParameters(p, method, params);

		PrintUtils.print(constraintViolationsM);
	}

	@SneakyThrows
	private static void method() {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		ExecutableValidator executableValidator = factory.getValidator().forExecutables();

		Producer p = new Producer("s");
		Method method = Producer.class.getDeclaredMethod("getName", int.class);
		//Object[] params = { 1 };
		String returnValue = "http://google.com";

		Set<ConstraintViolation<Producer>> constraintViolationsP = executableValidator
				.validateReturnValue(p, method, returnValue, Default.class);

		PrintUtils.print(constraintViolationsP);
	}
}
