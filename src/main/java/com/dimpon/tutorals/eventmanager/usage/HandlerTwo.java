package com.dimpon.tutorals.eventmanager.usage;

import com.dimpon.tutorals.eventmanager.common.EventHandler;
import com.dimpon.tutorals.eventmanager.common.EventManager;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class HandlerTwo implements EventHandler<MyEvent> {


    public HandlerTwo(EventManager<MyEvent> manager) {
        manager.registerHandler(MyEvent1.class, this);
        manager.registerHandler(MyEvent2.class, this);
    }

    @Override
    public void handle(MyEvent event) {
        log.info("Call:" + getClass() + " event:" + event + " context:" + event.getPayLoad());
    }
}
