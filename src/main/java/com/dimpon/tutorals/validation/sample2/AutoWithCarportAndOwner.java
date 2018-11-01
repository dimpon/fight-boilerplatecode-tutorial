package com.dimpon.tutorals.validation.sample2;

import com.dimpon.tutorals.validation.PrintUtils;
import com.dimpon.tutorals.validation.sample1.MyAuto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.*;
import javax.validation.constraints.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Dmitrii Ponomarev
 */
@Getter
@Setter
@Accessors(chain = true, fluent = true)
@RequiredArgsConstructor(staticName = "of")
@ToString
public class AutoWithCarportAndOwner {

	@Size(max = 17, min = 17, message = "Vin must have 17 symbols")
	private String vin;

	@NotNull(message = "Mileage is required")
	private Long mileage;

	@Valid
	private Carport carport;

	@Valid
	private Owner owner;

	@Min(value = 1, message = "Auto must have seats")
	private int seatsNumber;

	@NotEmpty
	private Map<@Email(message = "Need email as a key for ownersHistory") String, @NotNull String> ownersHistory;

	public Optional<@Min(value = 100, message = "Too small distance") Long> getMileage() {
		return Optional.of(mileage);
	}









	public static void main(String[] args) {

		AutoWithCarportAndOwner autoWithCarportAndOwner = AutoWithCarportAndOwner.of()
				.vin("1234567890qwertyu")
				.mileage(100L)
				.carport(Carport.of().engine("x45").model("passat"))
				.owner(Owner.of().age(18).drunk(false))
				.seatsNumber(5)
				.ownersHistory(new HashMap<String, String>() {
					{
                        put("peter@spb.ru", "Peter the First");
					}
				});

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<AutoWithCarportAndOwner>> constraintViolations = validator.validate(autoWithCarportAndOwner);

		PrintUtils.print(constraintViolations);

	}

}
