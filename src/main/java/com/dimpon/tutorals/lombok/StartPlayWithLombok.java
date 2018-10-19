package com.dimpon.tutorals.lombok;

import com.dimpon.tutorals.lombok.builders.sample3.RatherComplexTransformer;
import com.dimpon.tutorals.lombok.builders.sample3.Transformer;
import com.dimpon.tutorals.lombok.dto.sample2.SimpleDTOChains;
import com.dimpon.tutorals.lombok.dto.sample5.SimpleDTOWithLazy;
import com.dimpon.tutorals.lombok.miscellaneous.SneakException;
import com.dimpon.tutorals.lombok.npe.NonNullDto;
import com.dimpon.tutorals.lombok.ulility.MyLovelyUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class StartPlayWithLombok {


    public static void main(String[] args) throws Exception {

        Map<String,String> map = new HashMap<String,String>(){{
            put("k","v");
            put("k1","v1");
            put("k2","v2");
        }};

        String collect = map.entrySet().stream().sorted().map(e -> e.getKey() + " " + e.getValue()).collect(Collectors.joining(","));

        System.out.println(collect);


    }

}
