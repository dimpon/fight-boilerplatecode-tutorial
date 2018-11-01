package com.dimpon.tutorals.validation.sample5;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Dmitrii Ponomarev
 */
@Getter
@Setter
@Accessors(chain = true, fluent = true)
@RequiredArgsConstructor(staticName = "of")
@ToString
//@ValidateAutoAndOwner
public class XOwner {

	@Min(value = 18, message = "You're too young, bro!")
	private int age;

	@AssertFalse(message = "Take rest until you're sober")
	private boolean drunk = false;
}
