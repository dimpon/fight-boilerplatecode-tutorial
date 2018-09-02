package com.dimpon.tutorals.builder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Start {

    public static void main(String[] args) {

        ObjectABC object = BuilderABC.create().a("a").b("b").c("c").build();
        log.info(object.toString());

        ObjectEFGH obj = BuilderEFGH.create().a("a").b("b").c("c").d("d").e("e").f("f").g("g").h("h").build();
        log.info(obj.toString());
    }


}
