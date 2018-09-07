package com.dimpon.tutorals.validation;

import com.dimpon.tutorals.validation.custom.ValidateNObjects;
import com.dimpon.tutorals.validation.custom.AutoAndOwnerTuple;
import com.dimpon.tutorals.validation.custom.ValidateNObjectsCommandAutoAndOwner;
import com.dimpon.tutorals.validation.dto.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Dmitrii Ponomarev JSR 380
 */
@Slf4j
public class Start {

	public static void main(String[] args) {

		new Start().validateWithCrossParameterConstraint();

		new Start().validateConstructor();

		new Start().validateAuto();

		new Start().validateAutoAndOwner();

		new Start().validateAutoAndOwnerUsingTuple();
	}

	/////////////////////////////////////////

	@ValidateNObjects(value = {
			@ValidateNObjects.Element(Auto.class),
			@ValidateNObjects.Element(Owner.class)
	},
			command = ValidateNObjectsCommandAutoAndOwner.class,
			message = "What are you doing, man???"
	)
	private void validateAutoAndOwnerWithCrossParameterConstraint(Auto auto, Owner owner) {
		log.info(auto.toString() + owner.toString());
		//do some actions, or further validation
	}

	/////////////////////////////////////////////


	private void validateWithTuples(@AutoAndOwnerTuple Pair<Auto, Owner> pair) {
		log.info(pair.getLeft().toString() + pair.getRight().toString());
	}

	@SneakyThrows
	private void validateWithCrossParameterConstraint() {
		Auto auto = Auto.of();
		Owner owner = Owner.of().age(23).name("Dmitrii").drunk(true);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		Method method = this.getClass().getDeclaredMethod("validateAutoAndOwnerWithCrossParameterConstraint", Auto.class, Owner.class);

		Set<ConstraintViolation<Start>> constraintViolations = factory.getValidator()
				.forExecutables()
				.validateParameters(this, method, new Object[] { auto, owner }, Default.class);

		print(constraintViolations);

	}


	/////////////////////////////////////////////

	private void validateAutoAndOwner() {

		Auto auto = Auto.of();
		Owner owner = Owner.of().age(23).name("Dmitrii").drunk(true);

		OwnerAndAutoPair pair = new OwnerAndAutoPair(owner, auto);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		Validator validator = factory.getValidator();

		Set<ConstraintViolation<OwnerAndAutoPair>> constraintViolations = validator.validate(pair);

		print(constraintViolations);

	}

	//////////////////////////////////////////////

	@SneakyThrows
	private void validateAutoAndOwnerUsingTuple() {

		Auto auto = Auto.of();
		Owner owner = Owner.of().age(23).name("Dmitrii").drunk(true);

		Method method = this.getClass().getDeclaredMethod("validateWithTuples", Pair.class);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		Set<ConstraintViolation<Start>> constraintViolations = factory.getValidator()
				.forExecutables()
				.validateParameters(this, method, new Object[] { ImmutablePair.of(auto, owner) }, Default.class);

		print(constraintViolations);

	}

	////////////////////////////////////////////////

	private void validateAuto() {
		Auto auto = Auto.of()
				.vin("1234567890abcdefg")
				.mileage(500L)
				.seatsNumber(4)
				.prNumbers(Arrays.asList("a1", "a2", "a3", "a4", "a5"))

				.carport(Carport.of()
						.model("Almera")
						.engine("DS654")
						.country("Deutschland"))

				.ownersHistory(new HashMap<String, String>() {
					{
						put("peter@spb.ru", "Peter the First");
					}
				})
				.profile(new Profile("PRODx"));

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Auto>> constraintViolations = validator.validate(auto);

		print(constraintViolations);
	}

	@SneakyThrows
	private void validateConstructor() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		Set<ConstraintViolation<Producer>> constraintViolations = factory.getValidator().forExecutables()
				.validateConstructorParameters(Producer.class.getConstructor(String.class), new String[] { "s" }, Default.class);

		print(constraintViolations);

	}

	private <T> void print(Set<ConstraintViolation<T>> violations) {

		log.info("\n\n" +
				violations.stream()
						.map(v -> v.getMessage() + ": " + v.getInvalidValue())
						.collect(Collectors.joining("\n")));
	}

}
