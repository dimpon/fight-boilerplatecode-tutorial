package com.dimpon.tutorals;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Calculate {

	public static void main(String[] args) {


		int s = Integer.MAX_VALUE;

		int[] inita = new int[] { 0, 2, 0, 0, 2, 0, 0, 1, 0, 2, 2, 0, 1 };

		int sum1 = sum(inita, 1);

		int[] init = new int[] { 0, 1, 3, 2, 3, 1, 1, 1, 4, 1, Integer.MAX_VALUE };

		int i = 1;

		int sum = 0;

		for (;;) {

			int r = sum(init, i);

			if(r==0)
				break;

			sum = sum+r;
			i++;

		}

		log.info("sum="+sum);

	}

	/*
	 * N
	 */
	private static int sum(int[] arr, int minus) {
		int sum = 0;
		int tail = 0;
		boolean start = false;

		for (int anArr1 : arr) {

			int anArr = anArr1 - minus;

			if (start && anArr <= 0) {
				sum = sum + 1;
				tail = tail + 1;
			}

			if (anArr > 0) {
				start = true;
				tail = 0;
			}

		}

		return sum - tail;
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
