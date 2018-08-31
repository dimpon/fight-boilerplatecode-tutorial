package com.dimpon.tutorals.eventmanager.usage;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

/**
 * @author Dmitrii Ponomarev
 */
@Data
@Accessors(chain = true, fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventPayLoad {
    String company, product;
    BigDecimal amount;
}
