package com.dimpon.tutorals.lombok.delegation;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Fisher {

    public String iSeeOctopus(){
        return "Octopus!!!!";
    }

    public void catchIt(Octopus octopus){
      log.info("grolled octopus");
    }

    public String sayWhoIam(){
        return "Fisher!!!!";
    }


}
