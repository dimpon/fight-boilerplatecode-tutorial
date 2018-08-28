package com.dimpon.tutorals.lombok;

import com.dimpon.tutorals.lombok.builders.RatherComplexBuilder;
import com.dimpon.tutorals.lombok.builders.SomeComplexDTO;
import com.dimpon.tutorals.lombok.builders.Transformer;
import com.dimpon.tutorals.lombok.dto.SimpleDTO;
import com.dimpon.tutorals.lombok.dto.SimpleDTOChains;
import com.dimpon.tutorals.lombok.dto.SimpleDTOWithLazy;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

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


        SomeComplexDTO b = SomeComplexDTO.builder()
                .company("c")
                .name("n")
                .option("el1")
                .option("el2")
                .option("el3")
                .build();


        Transformer<String> comBuilder = RatherComplexBuilder.<String>builder()
                .filterParam("e2")
                .elements("e1")
                .elements("e2")
                .elements("e3")
                .elements("e4")/*
                .function(s -> s+"_A")
                .function(s -> s+"_B")
                .function(s -> s+"_C")*/
                .build();


        String s = comBuilder.doTransformation();

        System.out.println("r="+s);


    }

}
