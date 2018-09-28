package com.dimpon.tutorals.lombok.delegation.sample2;

import com.dimpon.tutorals.lombok.delegation.sample2.Octopus;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Fisher {

    public String iSeeOctopus(){
        return "Wow! Octopus!!!!";
    }


    public String sayWhoIam(){
        return "Im Fisher!!!!";
    }


}
