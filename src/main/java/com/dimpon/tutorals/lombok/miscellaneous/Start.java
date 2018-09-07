package com.dimpon.tutorals.lombok.miscellaneous;

import java.math.BigDecimal;

/**
 * @author Dmitrii Ponomarev
 */
public class Start {
    public static void main(String[] args) {

        try {
            new NoMoreExceptions().checkSum(new BigDecimal(-1));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass());
        }

    }

}
