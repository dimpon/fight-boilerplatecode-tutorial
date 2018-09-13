package com.dimpon.tutorals.commands;

import java.util.List;

@Command(value = CommandGo.class, elements = {
        @CommandElement("a"),
        @CommandElement("b")
}, annotations = {
        Override.class,
        Deprecated.class,
        CommandElement.class
})
@Command(value = CommandGo.class, elements = {
        @CommandElement({"a","b"}),
        @CommandElement("b")
})
@Command(value = CommandGo.class, elements = {
        @CommandElement("a"),
        @CommandElement("b")
})

@Bird
public  class  JusAClass {

    @Bird
    void me(@Bird int a) throws @Bird Exception{

        @Bird
        Object o = new @Bird Object();


        if(o==null)
            new @Bird Object();


        List<@Bird String> li;
    }


}
