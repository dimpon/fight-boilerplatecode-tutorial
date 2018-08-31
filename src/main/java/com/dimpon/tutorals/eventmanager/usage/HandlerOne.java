package com.dimpon.tutorals.eventmanager.usage;


import com.dimpon.tutorals.eventmanager.common.EventHandler;
import com.dimpon.tutorals.eventmanager.common.EventManager;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class HandlerOne implements EventHandler<EventContext, MyEvent> {


    public HandlerOne(EventManager<EventContext, MyEvent> manager) {
        manager.registerHandler(MyEvent1.class, this);
    }

    @Override
    public void handle(MyEvent event) {
        log.info("Call:" + getClass() + " event:" + event + " context:" + event.getPayLoad());
    }
}
