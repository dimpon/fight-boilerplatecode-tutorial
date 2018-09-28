package com.dimpon.tutorals.lombok.builders.sample2;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @author Dmitrii Ponomarev
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class ManyFieldsDTO {

	String field1, field2, field3, field4;
	int number1, number2, number3, number4;

	@Builder
	public ManyFieldsDTO(String field1, String field3, int number2) {
		this.field1 = field1;
		this.field3 = field3;
		this.number2 = number2;
	}

	public static void main(String[] args) {
		ManyFieldsDTO many = ManyFieldsDTO.builder()
				.field1("f1")
				.build();
	}

}
