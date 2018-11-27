package com.dimpon.tutorals.lombok.dto.sample6;

import lombok.Getter;

/**
 * @author Dmitrii Ponomarev
 */
public class MyLovelySingleton {

    @Getter(lazy = true)
    private final static MyLovelySingleton instance = new MyLovelySingleton();

    private MyLovelySingleton() {
        //do all init job here
    }
}
