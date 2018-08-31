package com.dimpon.tutorals.eventmanager.usage;

import com.dimpon.tutorals.eventmanager.common.EventManager;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start {

    public static void main(String[] args) {

        EventManager<EventContext, MyEvent> manager = EventManager.getManager();

        new HandlerOne(manager);
        new HandlerTwo(manager);


        EventContext eventContext = new EventContext()
                .company("company")
                .product("Swap")
                .amount(new BigDecimal(123.5));

        manager.fireEvent(new MyEvent1(MyEvent.EventType.START, eventContext));
        log.info("***");
        manager.fireEvent(new MyEvent2(MyEvent.EventType.IN_PROGRESS, eventContext));


        EventContextExt.of("name");

    }
}
