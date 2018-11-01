package com.dimpon.tutorals.validation.sample4;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Dmitrii Ponomarev
 */
@Data
public class Producer {
	private String name;

	public Producer(@NotBlank @Size( max = 7) String name) {
		this.name = name;
	}

	@Pattern(regexp = "^(http|https)://.*")
	public String getName(@Min(2) int number){
		return name+number;
	}
}
