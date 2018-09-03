package com.dimpon.tutorals.validation;

import com.dimpon.tutorals.validation.custom.ValidateAutoAndOwner;
import com.dimpon.tutorals.validation.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableValidator;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Dmitrii Ponomarev JSR 380
 */
@Slf4j
public class Start {
	public static void main(String[] args) {

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
				.profile(new Profile("PROD"));

		Owner owner = Owner.of().age(23).name("Dmitrii").drunk(false);


		OwnerAndAutoPair pair = new OwnerAndAutoPair(owner,auto);






		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		// ExecutableValidator executableValidator = factory.getValidator().forExecutables();

		Validator validator = factory.getValidator();

		//Set<ConstraintViolation<Auto>> validate = validator.validate(auto);


		Set<ConstraintViolation<OwnerAndAutoPair>> validate = validator.validate(pair);

		//Set<ConstraintViolation<Owner>> validate = validator.validate(owner);


		log.info("\n\n" +
				validate.stream()
						.map(v -> v.getMessage() + ": " + v.getInvalidValue())
						.collect(Collectors.joining("\n")));

	}



}
