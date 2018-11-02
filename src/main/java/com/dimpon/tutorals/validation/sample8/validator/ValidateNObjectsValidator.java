package com.dimpon.tutorals.validation.sample8.validator;

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

	private List<Class<?>> membersClasses = Collections.EMPTY_LIST;

	private Class<? extends ValidateNObjectsCommand> command;

	@Override
	public void initialize(ValidateNObjects annotation) {
		membersClasses = Arrays.stream(annotation.value()).collect(Collectors.toList());
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

		if (values.length != membersClasses.size())
			throw new IllegalArgumentException("Number of annotation element must be equal to membersClasses in method");

		if (command == null)
			throw new IllegalArgumentException("Need a class with validation logic");

		Class<?>[] classes = membersClasses.toArray(new Class<?>[0]);
		ValidateNObjectsCommand comm = command.getConstructor(classes).newInstance(values);
		return comm.validate();
	}

	@FunctionalInterface
	interface ValidateNObjectsCommand {
		boolean validate();
	}

}
