package com.dimpon.tutorals.validation.sample6;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.AssertFalse;

/**
 * @author Dmitrii Ponomarev
 */
@Getter
@Setter
@Accessors(chain = true, fluent = true)
@RequiredArgsConstructor(staticName = "of")
@ToString
public class YOwner {

    private int age;

    private boolean drunk = false;
}
