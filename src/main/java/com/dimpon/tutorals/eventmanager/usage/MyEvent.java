package com.dimpon.tutorals.eventmanager.usage;

import com.dimpon.tutorals.eventmanager.common.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Dmitrii Ponomarev
 */

public interface MyEvent extends Event<EventPayLoad> {

    enum EventType{
        START,
        IN_PROGRESS,
        STOP
    }
}
