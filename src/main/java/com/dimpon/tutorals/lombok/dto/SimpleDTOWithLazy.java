package com.dimpon.tutorals.lombok.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class SimpleDTOWithLazy {

    @Getter(lazy = true)
    private final Map<String, String> settings = fillSettings();



    private Map<String, String> fillSettings() {

        log.info("fillSettings");

        return new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
            put("key3", "value3");
        }};
    }




}
