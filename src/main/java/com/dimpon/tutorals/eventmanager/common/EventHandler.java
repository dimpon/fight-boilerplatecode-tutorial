package com.dimpon.tutorals.eventmanager.common;

public interface EventHandler<E extends Event> {
    void handle(E event);
}
