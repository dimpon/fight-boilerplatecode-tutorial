package com.dimpon.tutorals.validation.sample2;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Dmitrii Ponomarev
 */
@Data
public class Producer {
	private String name;

	public Producer(@NotBlank @Size(min = 2, max = 7) String name) {
		this.name = name;
	}
}
