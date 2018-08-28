package com.dimpon.tutorals.lombok.builders;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * @author Dmitrii Ponomarev
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class ManyFieldsDTO {

    String field1,field2,field3,field4;
    int number1,number2,number3,number4;

    @Builder
    public ManyFieldsDTO(String field1, String field3, int number2) {
        this.field1 = field1;
        this.field3 = field3;
        this.number2 = number2;
    }
}
