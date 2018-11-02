package com.dimpon.tutorals.validation.sample8;

import com.dimpon.tutorals.validation.sample1_1.Auto;
import com.dimpon.tutorals.validation.sample2.Owner;
import com.dimpon.tutorals.validation.sample8.dto.ZAuto;
import com.dimpon.tutorals.validation.sample8.dto.ZOwner;
import com.dimpon.tutorals.validation.sample8.validator.ValidateNObjects;
import com.dimpon.tutorals.validation.sample8.validator.ValidateNObjectsCommandAutoAndOwner;

public interface ServiceX {

	@ValidateNObjects(value = { ZAuto.class, ZOwner.class },
            command = ValidateNObjectsCommandAutoAndOwner.class, message = "Not allowed to drive! ${validatedValue[0]} and ${validatedValue[1]} {value}...")
	void validateAutoAndOwnerWithCrossParameterConstraint(ZAuto auto, ZOwner owner);
}
