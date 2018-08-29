package com.dimpon.tutorals.lombok;

import com.dimpon.tutorals.lombok.builders.ManyFieldsDTO;
import com.dimpon.tutorals.lombok.builders.RatherComplexTransformer;
import com.dimpon.tutorals.lombok.builders.SomeComplexDTO;
import com.dimpon.tutorals.lombok.builders.Transformer;
import com.dimpon.tutorals.lombok.dto.SimpleDTOChains;
import com.dimpon.tutorals.lombok.dto.SimpleDTOWithLazy;
import com.dimpon.tutorals.lombok.ulility.MyLovelyUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        Collection<String> strings = MyLovelyUtils.applyFunctionOnOnCollection(ele, s1 -> s1 + "_1");


        System.out.println(MyLovelyUtils.sayHello());


    }

}
