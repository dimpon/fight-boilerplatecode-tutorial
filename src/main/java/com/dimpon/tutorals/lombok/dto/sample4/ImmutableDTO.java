package com.dimpon.tutorals.lombok.dto.sample4;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author Dmitrii Ponomarev
 */

/*
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE) @Getter
*/
@Value
public class ImmutableDTO {
    String name, company, product;
}
