package com.dimpon.tutorals.lombok.builders;

import lombok.Builder;
import lombok.Singular;

import java.util.List;
import java.util.PrimitiveIterator;

/**
 * @author Dmitrii Ponomarev
 */
@Builder
public class SomeComplexDTO {

    private String name, $company, familyname;

    @Singular
    private List<String> options;

}
