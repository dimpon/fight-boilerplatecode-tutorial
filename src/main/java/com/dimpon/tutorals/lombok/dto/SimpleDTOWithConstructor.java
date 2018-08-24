package com.dimpon.tutorals.lombok.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Dmitrii Ponomarev
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleDTOWithConstructor {
    private String name, familyName;
    private int age;
}
