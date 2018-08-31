package com.dimpon.tutorals.validation.dto;

/**
 * @author Dmitrii Ponomarev
 */
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
@RequiredArgsConstructor(staticName = "of")
public class Auto {

	@Size(max = 17, min = 17, message = "Vin must have 17 symbols")
	private String vin;

	@NotNull(message = "Mileage is required")
	private Long mileage;

	@Size(min = 5, message = "Must contain 5 PR numbrs")
	private List<@NotBlank(message = "PR numbers must not be blank") String> prNumbers;

	@Valid
	private Carport carport;

	@Min(value = 1, message = "Auto must have seats")
	private int seatsNumber;

	private Map<@Email String, @NotNull String> ownersHistory;

	public Optional<@Min(value = 100,message = "Too small distance") Long> getMileage() {
		return Optional.of(mileage);
	}
}
