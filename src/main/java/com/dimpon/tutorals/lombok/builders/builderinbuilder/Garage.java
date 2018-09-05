package com.dimpon.tutorals.lombok.builders.builderinbuilder;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dmitrii Ponomarev
 */
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

	public String autosDetails(){
        return autos.stream().map(this::join).collect(Collectors.joining("|"));
    }

	private String join(Auto auto) {
		return new StringBuilder().append(auto.brand)
				.append('/')
				.append(auto.model)
				.append('/')
				.append(auto.license)
				.append('/')
				.append(auto.vin).toString();
	}

}
