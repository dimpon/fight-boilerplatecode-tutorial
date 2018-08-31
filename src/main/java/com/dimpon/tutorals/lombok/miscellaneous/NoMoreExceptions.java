package com.dimpon.tutorals.lombok.miscellaneous;

import lombok.SneakyThrows;

import java.math.BigDecimal;

/**
 * @author Dmitrii Ponomarev
 */
public class NoMoreExceptions {

    @SneakyThrows(WrongSumException.class)
    public void checkSum(BigDecimal sum) {

        if (sum.signum() == -1)
            throw new WrongSumException();

    }

}



