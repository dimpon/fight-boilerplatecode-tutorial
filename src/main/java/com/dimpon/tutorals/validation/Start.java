package com.dimpon.tutorals.validation;

import com.dimpon.tutorals.validation.dto.Auto;
import com.dimpon.tutorals.validation.dto.Carport;
import com.dimpon.tutorals.validation.dto.Profile;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start {
	public static void main(String[] args) {

		Auto auto = Auto.of()
				.vin("1234567890abcdef")
				.mileage(50L)
				.seatsNumber(4)
				.prNumbers(Arrays.asList("a1", "a2", "a3", "a4", "a5"))

				.carport(Carport.of()
						.model("")
						.engine("")
						.country(""))

				.ownersHistory(new HashMap<String, String>() {
					{
						put("peter@spb.ru", "Peter the First");
					}
				})
				.profile(new Profile(""));

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		// ExecutableValidator executableValidator = factory.getValidator().forExecutables();

		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Auto>> validate = validator.validate(auto);

		log.info("\n\n" +
				validate.stream()
						.map(v -> v.getMessage() + ": " + v.getInvalidValue())
						.collect(Collectors.joining("\n")));

	}

}
