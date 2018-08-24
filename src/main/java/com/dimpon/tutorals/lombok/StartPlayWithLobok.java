package com.dimpon.tutorals.lombok;

import com.dimpon.tutorals.lombok.dto.SimpleDTO;
import com.dimpon.tutorals.lombok.dto.SimpleDTOChains;
import com.dimpon.tutorals.lombok.dto.SimpleDTOWithLazy;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class StartPlayWithLobok {


    public static void main(String[] args) {

        log.info("hello zusamen!!!");
        SimpleDTOWithLazy dto = new SimpleDTOWithLazy();


        SimpleDTOChains cha = new SimpleDTOChains();
        cha.company("co")
                //.family("fa")
                .something("so")
                .name("na");


    }

}
