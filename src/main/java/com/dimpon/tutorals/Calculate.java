package com.dimpon.tutorals;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Calculate {

    public static void main(String[] args) {


        int s = Integer.MAX_VALUE;

        int[] inita = new int[]{11, 12, 11, 224, 12, 55, 55, 64};

        int a = 0;
        int b = 0;

        for (int i1 : inita) {
            a = a ^ i1;
            b = b ^ cycleShiftLeft(i1, i1);
        }

        int x = 1;
        for (; ; ) {
            if ((cycleShiftLeft(x, x) ^ (cycleShiftLeft(x ^ a, x ^ a))) == b)
                break;
            x++;
        }

        log.info("a =" + a);
        log.info("b =" + b);
        log.info("");
        log.info("x =" + x);
        log.info("y =" + (a^x));





    }
    /*

    x ^ y = a; y = a^x

    x<<x ^ y<<y = b





     */


    private static int cycleShiftLeft(int val, int shift) {
        int i = shift % 32;// real shift
        int a1 = val >>> 32 - i, a2 = val << i;
        return a1 | a2;
    }

    private static int cycleShiftRight(int val, int shift) {
        int i = shift % 32;// real shift
        int a1 = val << 32 - i, a2 = val >>> i;
        return a1 | a2;
    }

}
