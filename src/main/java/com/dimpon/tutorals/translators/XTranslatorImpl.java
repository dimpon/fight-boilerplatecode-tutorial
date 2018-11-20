package com.dimpon.tutorals.translators;

import com.dimpon.tutorals.validation.sample1_1.Auto;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class XTranslatorImpl implements XTranslator<Auto> {


    @Override
    public String translate(Auto auto) {
        return auto.vin()+auto.mileage();
    }

}
