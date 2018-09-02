package com.dimpon.tutorals.builder.base;

public abstract class BaseBuilder<B extends BaseBuilder<B, O>, O> {

    @SuppressWarnings("unchecked")
    protected B builder = (B) this;

    public abstract O build();
}
