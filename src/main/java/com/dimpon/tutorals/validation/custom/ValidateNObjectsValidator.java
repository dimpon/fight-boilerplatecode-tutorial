package com.dimpon.tutorals.validation.custom;

import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dmitrii Ponomarev
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ValidateNObjectsValidator implements ConstraintValidator<ValidateNObjects, Object[]> {

	private List<Class<?>> arguments = Collections.EMPTY_LIST;

	private Class<? extends ValidateFewObjectsCommand> command;

	@Override
	public void initialize(ValidateNObjects annotation) {
		arguments = Arrays.stream(annotation.value()).map(ValidateNObjects.Element::value).collect(Collectors.toList());
		command = annotation.command();
	}

	@SneakyThrows({
			NoSuchMethodException.class,
			SecurityException.class,
			InstantiationException.class,
			IllegalAccessException.class,
			InvocationTargetException.class
	})
	@Override
	public boolean isValid(Object[] values, ConstraintValidatorContext context) {

		if (values.length != arguments.size())
			throw new IllegalArgumentException("Number of annotation element must be equal to arguments in method");

		if (command == null)
			throw new IllegalArgumentException("Neew a command with validation logic");

		Class<?>[] classes = arguments.toArray(new Class<?>[0]);
		ValidateFewObjectsCommand comm = command.getConstructor(classes).newInstance(values);
		return comm.validate();
	}

	@FunctionalInterface
	interface ValidateFewObjectsCommand {
		boolean validate();
	}

}
