package com.dimpon.tutorals.lombok.dto.sample2;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Tolerate;

/**
 * @author Dmitrii Ponomarev
 */
@Accessors(chain = true, fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class SimpleDTOChains {
    String something;
    String name, $family, company;
}
