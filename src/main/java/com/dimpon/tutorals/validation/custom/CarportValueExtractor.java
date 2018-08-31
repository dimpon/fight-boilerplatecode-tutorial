package com.dimpon.tutorals.validation.custom;

import com.dimpon.tutorals.validation.dto.Carport;

import javax.validation.valueextraction.ExtractedValue;
import javax.validation.valueextraction.UnwrapByDefault;
import javax.validation.valueextraction.ValueExtractor;

/**
 * @author Dmitrii Ponomarev
 */

@UnwrapByDefault
public class CarportValueExtractor implements ValueExtractor<@ExtractedValue(type = String.class) Carport> {

	@Override
	public void extractValues(@ExtractedValue(type = String.class) Carport originalValue, ValueReceiver receiver) {
		String value = originalValue.country() + originalValue.engine() + originalValue.model();
		receiver.value(null, value);
	}
}
