package com.dimpon.tutorals;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Calculate {

	public static void main(String[] args) {

		for (int x = 0; x < 50; x++) {

            int y = cycleShiftLeft(x, x);

            log.info("x=" + x + "  y=" + y +"  plays with y L:"+(cycleShiftLeft(y,y))+"  R:"+(cycleShiftRight(y,y)));


		}

	}

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
