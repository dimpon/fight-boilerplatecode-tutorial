package com.dimpon.tutorals.lombok.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author Dmitrii Ponomarev
 */
@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(staticName = "create")
public class SimpleDTOWithConstructor {
    private final String name, familyName;
    private int age;
}
