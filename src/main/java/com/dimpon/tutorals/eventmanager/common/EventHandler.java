package com.dimpon.tutorals.eventmanager.common;

public interface EventHandler<T, E extends Event<T>> {
    void handle(E event);
}
