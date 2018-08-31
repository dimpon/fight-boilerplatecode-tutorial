package com.dimpon.tutorals.eventmanager.usage;

import com.dimpon.tutorals.eventmanager.common.Event;

/**
 * @author Dmitrii Ponomarev
 */
public interface MyEvent extends Event<EventContext> {

    enum EventType{
        START,
        IN_PROGRESS,
        STOP
    }
}
