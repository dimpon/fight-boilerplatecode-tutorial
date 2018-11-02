package com.dimpon.tutorals.validation.sample8.extractor;

import com.dimpon.tutorals.validation.sample7.Profile;
import com.dimpon.tutorals.validation.sample8.dto.ZAuto;

import javax.validation.valueextraction.ExtractedValue;
import javax.validation.valueextraction.UnwrapByDefault;
import javax.validation.valueextraction.ValueExtractor;

/**
 * @author Dmitrii Ponomarev
 */
@UnwrapByDefault
public class ZAutoExtractor implements ValueExtractor<@ExtractedValue(type = String.class) ZAuto> {

    @Override
    public void extractValues(@ExtractedValue(type = String.class) ZAuto originalValue, ValueReceiver receiver) {
        String value = originalValue.toString();
        receiver.value(null, value);
    }
}

