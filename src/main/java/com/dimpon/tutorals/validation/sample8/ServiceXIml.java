package com.dimpon.tutorals.validation.sample8;

import com.dimpon.tutorals.validation.sample1_1.Auto;
import com.dimpon.tutorals.validation.sample2.Owner;
import com.dimpon.tutorals.validation.sample8.dto.ZAuto;
import com.dimpon.tutorals.validation.sample8.dto.ZOwner;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class ServiceXIml implements ServiceX {

    public void validateAutoAndOwnerWithCrossParameterConstraint(ZAuto auto, ZOwner owner) {
        log.info(auto.toString() + owner.toString());
        //do some actions, or further validation
    }

}
