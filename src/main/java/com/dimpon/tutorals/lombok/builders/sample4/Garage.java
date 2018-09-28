package com.dimpon.tutorals.lombok.builders.sample4;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static com.dimpon.tutorals.lombok.builders.sample4.Garage.Auto.Brand.SHKODA;
import static com.dimpon.tutorals.lombok.builders.sample4.Garage.Auto.Brand.VW;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Garage {

	@Builder.Default
	String owner = "Dmitry";

	@Singular("car")
	List<Auto> autos;

	@Builder(builderMethodName = "of", buildMethodName = "create")
	@FieldDefaults(level = AccessLevel.PRIVATE)
	@Getter
	@ToString
	public static class Auto {

		public enum Brand {
			VW,
			AUDI,
			PORSCHE,
			SHKODA
		}

		Brand brand;

		String model, license;
		@Builder.Default
		Integer vin = 124;
	}

	public String autosDetails() {
		return autos.stream()
				.map(Auto::toString)
				.collect(Collectors.joining("\n","\n\n","\nxxxxx"));
	}


	/////////////////////////////


	public static void main(String[] args) {

		Garage garage = Garage.builder()
				.owner("John Doe")
				.car(Auto.of()
						.brand(VW)
						.model("Polo")
						.vin(342342342)
						.license("unlimited")
						.create())
				.car(Auto.of()
						.brand(SHKODA)
						.model("Oktavia")
						.vin(21321434)
						.create())
				.build();

		log.info(garage.autosDetails());
	}

}
