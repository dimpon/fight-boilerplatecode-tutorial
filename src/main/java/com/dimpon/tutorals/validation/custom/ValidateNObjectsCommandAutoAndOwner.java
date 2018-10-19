package com.dimpon.tutorals.validation.custom;

import com.dimpon.tutorals.validation.sample1.Auto;
import com.dimpon.tutorals.validation.sample1.Owner;
import lombok.AllArgsConstructor;

/**
 * @author Dmitrii Ponomarev
 */
@AllArgsConstructor
public class ValidateNObjectsCommandAutoAndOwner implements ValidateNObjectsValidator.ValidateNObjectsCommand {

	private final Auto auto;
	private final Owner owner;

	@Override
	public boolean validate() {
		return !owner.drunk() || auto.broken();
	}
}
