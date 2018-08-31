package com.dimpon.tutorals.eventmanager.usage;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Dmitrii Ponomarev
 */
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MyEvent2 implements MyEvent {

    EventType type;
    EventContext context;

    @Override
    public EventContext getPayLoad() {
        return context;
    }
}
