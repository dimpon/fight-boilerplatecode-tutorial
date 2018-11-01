package com.dimpon.tutorals.methodannprocessor;

public interface Hound {

    @LevelOfBarking(level = 7)
    void bark();
}
