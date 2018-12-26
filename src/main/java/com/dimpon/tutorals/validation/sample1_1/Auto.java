package com.dimpon.tutorals.validation.sample1_1;

/**
 * @author Dmitrii Ponomarev
 */
import com.dimpon.tutorals.validation.sample2.Carport;
import com.dimpon.tutorals.validation.sample7.Profile;
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
@ToString
public class Auto {

	@Size(max = 17, min = 17, message = "Vin must have 17 symbols")
	private String vin;

	@NotNull(message = "Mileage is required")
	private Long mileage;

	@Size(min = 5, message = "Must contain 5 PR numbrs")
	private List<@NotBlank(message = "PR numbers must not be blank") String> prNumbers;

	@Valid
	private Carport carport;

//	@Equals(value = "PROD", message = "Profile must be PROD")
	@NotBlank(message = "Profile cannot be blank")
	@Max(value = 4, message = "More then 4 symbols")
	private Profile profile;

	@Min(value = 1, message = "Auto must have seats")
	private int seatsNumber;

	private boolean broken = false;

	private Map<@Email(message = "need email like key create map") String, @NotNull String> ownersHistory;

	public Optional<@Min(value = 100, message = "Too small distance") Long> getMileage() {
		return Optional.of(mileage);
	}
}
