package com.dimpon.tutorals.validation.sample7.extractor;

import com.dimpon.tutorals.validation.sample7.Profile;

import javax.validation.valueextraction.ExtractedValue;
import javax.validation.valueextraction.UnwrapByDefault;
import javax.validation.valueextraction.ValueExtractor;

/**
 * @author Dmitrii Ponomarev
 */

@UnwrapByDefault
public class ProfileValueExtractor implements ValueExtractor<@ExtractedValue(type = String.class) Profile> {

	@Override
	public void extractValues(@ExtractedValue(type = String.class) Profile originalValue, ValueReceiver receiver) {
		String value = originalValue.getCompany();
		receiver.value(null, value);
	}
}
