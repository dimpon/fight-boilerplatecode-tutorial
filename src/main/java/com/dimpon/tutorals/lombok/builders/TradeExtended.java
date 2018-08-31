package com.dimpon.tutorals.lombok.builders;

import lombok.experimental.SuperBuilder;

/**
 * @author Dmitrii Ponomarev
 */
@SuperBuilder
public class TradeExtended extends Trade {
    private String approver;
}
