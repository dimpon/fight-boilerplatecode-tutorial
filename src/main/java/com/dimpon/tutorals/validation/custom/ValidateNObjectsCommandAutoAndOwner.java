package com.dimpon.tutorals.validation.custom;

import com.dimpon.tutorals.validation.dto.Auto;
import com.dimpon.tutorals.validation.dto.Owner;
import lombok.AllArgsConstructor;

/**
 * @author Dmitrii Ponomarev
 */
@AllArgsConstructor
public class ValidateNObjectsCommandAutoAndOwner implements ValidateNObjectsValidator.ValidateFewObjectsCommand {

	private final Auto auto;
	private final Owner owner;

	@Override
	public boolean validate() {
		return !owner.drunk() || auto.broken();
	}
}
