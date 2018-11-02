package com.dimpon.tutorals.validation.sample8.validator;

import com.dimpon.tutorals.validation.sample1_1.Auto;
import com.dimpon.tutorals.validation.sample2.Owner;
import com.dimpon.tutorals.validation.sample8.dto.ZAuto;
import com.dimpon.tutorals.validation.sample8.dto.ZOwner;
import lombok.AllArgsConstructor;

/**
 * @author Dmitrii Ponomarev
 */
@AllArgsConstructor
public class ValidateNObjectsCommandAutoAndOwner implements ValidateNObjectsValidator.ValidateNObjectsCommand {

	private final ZAuto auto;
	private final ZOwner owner;

	@Override
	public boolean validate() {
		return !owner.drunk() && !auto.broken();
	}
}
