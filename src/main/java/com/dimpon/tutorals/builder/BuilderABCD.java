package com.dimpon.tutorals.builder;

import com.dimpon.tutorals.builder.base.BaseBuilder;
import com.dimpon.tutorals.builder.base.BaseSimpleBuilder;
import lombok.AccessLevel;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PROTECTED)
@Accessors(chain = true, fluent = true)
public abstract class BuilderABCD<B extends BuilderABCD<B, O>, O extends ObjectABCD> extends BaseBuilder<B, O> {
    String a, b, c, d;

    public B a(String a) {
        this.a = a;
        return builder;
    }

    public B b(String b) {
        this.b = b;
        return builder;
    }

    public B c(String c) {
        this.c = c;
        return builder;
    }

    public B d(String d) {
        this.d = d;
        return builder;
    }
}
