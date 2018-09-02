package com.dimpon.tutorals.builder;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(staticName = "create")
@Setter
@Accessors(chain = true, fluent = true)
public final class BuilderEFGH extends BuilderABCD<BuilderEFGH,ObjectEFGH>  {
    String e,f,g,h;

    @Override
    public ObjectEFGH build() {
        return new ObjectEFGH(a,b,c,d,e,f,g,h);
    }
}
