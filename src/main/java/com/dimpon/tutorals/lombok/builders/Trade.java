package com.dimpon.tutorals.lombok.builders;

import lombok.experimental.SuperBuilder;

/**
 * @author Dmitrii Ponomarev
 */
@SuperBuilder
public class Trade {
    private String currency, seller, buyer;
}
