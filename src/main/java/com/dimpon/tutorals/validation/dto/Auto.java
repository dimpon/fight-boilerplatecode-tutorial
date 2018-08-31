package com.dimpon.tutorals.validation.dto;

/**
 * @author Dmitrii Ponomarev
 */
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
@RequiredArgsConstructor(staticName = "of")
public class Auto {

	@Size(max = 17, min = 17, message = "Vin must have 17 symbols")
	private String vin;

	@NotNull(message = "Mileage is required")
	private Long mileage;

	@Size(min = 5, message = "Must contain 5 pr numbrs")
	private List<String> prNumbers;

	@Valid
	private Carport carport;

	@Min(value = 1, message = "Auto must have seats")
	private int seatsNumber;

}
