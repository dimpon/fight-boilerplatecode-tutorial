package com.dimpon.tutorals.eventmanager.common;

import lombok.AccessLevel;
import lombok.Synchronized;
import lombok.experimental.FieldDefaults;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Dmitrii Ponomarev
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventManager<E extends Event> {

	static EventManager manager = new EventManager();
	Map<Class<? extends E>, Set<EventHandler<E>>> handlers = new ConcurrentHashMap<>();

	@Synchronized
	@SuppressWarnings("unchecked")
	public static <E extends Event> EventManager<E> getManager() {
		return (EventManager<E>) manager;
	}

	public void registerHandler(final Class<? extends E> clazz, final EventHandler<E> handler) {

		if (handlers.containsKey(clazz))
			handlers.get(clazz).add(handler);
		else
			handlers.put(clazz, new HashSet<>(Collections.singletonList(handler)));
	}

	public void fireEvent(final E event) {
		@SuppressWarnings("unchecked")
		Class<E> clazz = (Class<E>) event.getClass();
		handlers.get(clazz).forEach(h -> h.handle(event));
	}
}
