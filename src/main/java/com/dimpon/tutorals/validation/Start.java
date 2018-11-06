package com.dimpon.tutorals.validation;

import com.dimpon.tutorals.validation.sample8.validator.ValidateNObjects;
import com.dimpon.tutorals.validation.sample8.validator.ValidateNObjectsCommandAutoAndOwner;
import com.dimpon.tutorals.validation.sample2.Carport;
import com.dimpon.tutorals.validation.sample2.Owner;
import com.dimpon.tutorals.validation.sample1_1.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * @author Dmitrii Ponomarev JSR 380
 */
@Slf4j
public class Start {

	public static void main(String[] args) {

		new Start().validateWithCrossParameterConstraint();

		new Start().validateConstructor();

		new Start().validateAuto();

		//new Start().validateAutoAndOwner();

		new Start().validateAutoAndOwnerUsingTuple();

	}

	/////////////////////////////////////////

	/*@ValidateNObjects(value = {
			@ValidateNObjects.Element(Auto.class),
			@ValidateNObjects.Element(Owner.class)
	},
			command = ValidateNObjectsCommandAutoAndOwner.class,
			message = "What are you doing, man???"
	)*/
	private void validateAutoAndOwnerWithCrossParameterConstraint(Auto auto, Owner owner) {
		log.info(auto.toString() + owner.toString());
		//do some actions, or further validation
	}

	/////////////////////////////////////////////




	@SneakyThrows
	private void validateWithCrossParameterConstraint() {
		Auto auto = Auto.of();
		/*XOwner owner = XOwner.of().age(23).name("Dmitrii").drunk(true);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		Method method = this.getClass().getDeclaredMethod("validateAutoAndOwnerWithCrossParameterConstraint", Auto.class, XOwner.class);

		Set<ConstraintViolation<Start>> constraintViolations = factory.getValidator()
				.forExecutables()
				.validateParameters(this, method, new Object[] { auto, owner }, Default.class);

		PrintUtils.print(constraintViolations);
*/
	}


	/////////////////////////////////////////////

	/*private void validateAutoAndOwner() {

		Auto auto = Auto.of();
		XOwner owner = XOwner.of().age(23).name("Dmitrii").drunk(true);

		OwnerAndAutoPair pair = new OwnerAndAutoPair(owner, auto);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		Validator validator = factory.getValidator();

		Set<ConstraintViolation<OwnerAndAutoPair>> constraintViolations = validator.validate(pair);

		PrintUtils.print(constraintViolations);

	}*/

	//////////////////////////////////////////////

	@SneakyThrows
	private void validateAutoAndOwnerUsingTuple() {

		/*Auto auto = Auto.of();
		XOwner owner = XOwner.of().age(23).name("Dmitrii").drunk(true);

		Method method = this.getClass().getDeclaredMethod("validateWithTuples", Pair.class);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		Set<ConstraintViolation<Start>> constraintViolations = factory.getValidator()
				.forExecutables()
				.validateParameters(this, method, new Object[] { ImmutablePair.of(auto, owner) }, Default.class);

		PrintUtils.print(constraintViolations);*/

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
						.engine("DS654"))
						//.country("Deutschland"))

				/*.ownersHistory(new HashMap<String, String>() {
					{
						put("peter@spb.ru", "Peter the First");
					}
				})*/
				//.profile(new Profile("PRODx"))
				;

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Auto>> constraintViolations = validator.validate(auto);

		PrintUtils.print(constraintViolations);
	}

	@SneakyThrows
	private void validateConstructor() {


	}



}
