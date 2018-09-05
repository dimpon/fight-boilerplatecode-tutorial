package com.dimpon.tutorals.lombok;

import com.dimpon.tutorals.lombok.builders.*;
import com.dimpon.tutorals.lombok.dto.SimpleDTOChains;
import com.dimpon.tutorals.lombok.dto.SimpleDTOWithLazy;
import com.dimpon.tutorals.lombok.miscellaneous.SneakException;
import com.dimpon.tutorals.lombok.npe.NonNullDto;
import com.dimpon.tutorals.lombok.ulility.MyLovelyUtils;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class StartPlayWithLombok {


    public static void main(String[] args) throws Exception {

        Thread.sleep(10000);



        log.info("hello zusamen!!!");
        SimpleDTOWithLazy dto = new SimpleDTOWithLazy();


        SimpleDTOChains cha = new SimpleDTOChains();
        cha.company("co")
                //.family("fa")
                .something("so")
                .name("na");


        SomeComplexDTO b = SomeComplexDTO.builder()
                //.company("c")
                .name("n")
                .option("el1")
                .option("el2")
                .option("el3")
                .build();


        Transformer<String> comBuilder = RatherComplexTransformer.<String>builder()
                .filterParam("e2")
                .elements("e1")
                .elements("e2")
                .elements("e3")
                .elements("e4")
                .function(s -> s + "_A")
                .function(s -> s + "_B")
                .function(s -> s + "_C")
                .build();



        String s = comBuilder.doTransformation();

        System.out.println("r=" + s);

        ManyFieldsDTO many = ManyFieldsDTO.builder().field1("f1").build();


        List<String> ele = new ArrayList<String>(){{add("a");add("b");add("c");}};
        Collection<String> strings = MyLovelyUtils.applyFunctionOnCollection(ele, s1 -> s1 + "_1");


        System.out.println(MyLovelyUtils.sayHello());


        SneakException me = new SneakException("./src/main/java/com/dimpon/tutorals");
        me.loadData();

        NonNullDto non = new NonNullDto();
        //non.pass(null);


    }

}