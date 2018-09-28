package com.dimpon.tutorals.eventmanager.usage;

/**
 * @author Dmitrii Ponomarev
 */

public class EventContextExt {

	private String name;
	private String surname;
	private String $lastname;

    private EventContextExt() {
    }

    @java.beans.ConstructorProperties({"name", "surname"})
    private EventContextExt(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public static EventContextExt of() {
        return new EventContextExt();
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }
}
