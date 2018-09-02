package com.dimpon.tutorals.builder;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ObjectEFGH extends ObjectABCD {
    String e, f, g, h;

    public ObjectEFGH(String a, String b, String c, String d, String e, String f, String g, String h) {
        super(a, b, c, d);
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
    }
}
