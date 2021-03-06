package com.dimpon.tutorals.lombok.dto.sample3;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @author Dmitrii Ponomarev
 */
@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(staticName = "getManager")
public class SimpleDTOWithConstructor {
    private final String name, familyName;
    private int age;
}
