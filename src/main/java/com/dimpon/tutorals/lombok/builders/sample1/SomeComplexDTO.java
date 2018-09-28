package com.dimpon.tutorals.lombok.builders.sample1;

import lombok.Builder;
import lombok.Singular;

import java.util.List;
import java.util.PrimitiveIterator;

/**
 * @author Dmitrii Ponomarev
 */
@Builder
public class SomeComplexDTO {

	private String name, $company, familyname;

	@Singular
	private List<String> options;

	public static void main(String[] args) {

		SomeComplexDTO b = SomeComplexDTO.builder()
				// .company("c")
				.name("n")
				.option("el1")
				.option("el2")
				.option("el3")
				.build();
	}

}
