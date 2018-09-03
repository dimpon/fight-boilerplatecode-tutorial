package com.dimpon.tutorals.validation.dto;

import com.dimpon.tutorals.validation.custom.Equals;
import com.dimpon.tutorals.validation.custom.ValidateAutoAndOwner;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;

/**
 * @author Dmitrii Ponomarev
 */
@Getter
@Setter
@Accessors(chain = true, fluent = true)
@RequiredArgsConstructor(staticName = "of")
@ToString
@ValidateAutoAndOwner
public class Owner {

	@Min(value = 18, message = "You're too young, bro!")
	private int age;

	@Equals(value = "Dmitrii", message = "Only Dmitrii is allowed")
	private String name;

	@AssertFalse(message = "take rest until you're sober")
	private boolean drunk = false;
}
