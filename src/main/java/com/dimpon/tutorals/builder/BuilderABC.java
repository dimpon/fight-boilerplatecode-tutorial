package com.dimpon.tutorals.builder;

import com.dimpon.tutorals.builder.base.BaseSimpleBuilder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(staticName = "create")
@Setter
@Accessors(chain = true, fluent = true)
public class BuilderABC extends BaseSimpleBuilder<ObjectABC> {

    String a, b, c;

    @Override
    public ObjectABC build() {
        return new ObjectABC(a,b,c);
    }
}

