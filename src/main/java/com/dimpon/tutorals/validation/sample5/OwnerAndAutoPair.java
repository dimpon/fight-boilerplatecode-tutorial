package com.dimpon.tutorals.validation.sample5;

import com.dimpon.tutorals.validation.sample5.validator.ValidateAutoAndOwner;
import com.dimpon.tutorals.validation.sample1_1.Auto;
import com.dimpon.tutorals.validation.sample2.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;

/**
 * @author Dmitrii Ponomarev
 */
@ValidateAutoAndOwner(message = "Ha! you're drunk and auto is broken!")
@Getter
@AllArgsConstructor
@ToString
public class OwnerAndAutoPair {
	@Valid
	final XOwner owner;
	@Valid
	final XAuto auto;
}
