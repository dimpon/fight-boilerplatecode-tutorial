package com.dimpon.tutorals.lombok.delegation;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Octopus extends Mollusk {

    public void leg1(){
      log.info("leg1");
    }

    public void leg2(){
        log.info("leg2");
    }

    public void leg3(){
        log.info("leg3");
    }


    /*public String sayWhoIam(){
        return "Octopus!!!!";
    }*/


}
