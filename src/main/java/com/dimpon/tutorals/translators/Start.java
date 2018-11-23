package com.dimpon.tutorals.translators;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start {

	public static void main(String[] args) throws Exception {

		java.util.Random random = new Random();
		int SIZE = 1_000_000_000;

		// fill the random int array of SIZE
		int[] values = new int[SIZE];

		for (int i = 0; i < SIZE - 2; i = i + 2) {
			int s = random.nextInt(3000);
			values[i] = s;
			values[i + 1] = s;
		}

		// generate 2 unique elements and put it in the end of array (the check if they are already in the array must be added)
		int X = random.nextInt(3000), Y = random.nextInt(3000);

		log.info("X=" + X);
		log.info("Y=" + Y);
		values[SIZE - 2] = X;
		values[SIZE - 1] = Y;

		// shuffle array
		shuffleArray(values);

		// find 2 unique elements and print
		findAndPrint(values);
	}

	/**
	 * basically we know 2 values, a and b:
	 * <p>
	 * x^y = b
	 * <p>
	 * (x << x)^(y << y) = a
	 * <p>
	 * knowing this we can find x and y
	 */
	private static void findAndPrint(int[] all) {

		int[] acc = new int[32];
		int mask = 0b00000000_00000000_00000000_00000001;

		int xor = 0;

		for (int u : all) {
			for (int i = 0; i < acc.length; i++) {
				if (((mask << i) & u) != 0)
					acc[i] = acc[i] ^ u;
			}
			xor = xor ^ u;
		}

		int x=0;
		for (int a : acc) {
			if (a != 0 && a != xor) {
				x = a;
				break;
			}
		}

		log.info("X="+x);
		log.info("Y="+(xor^x));
	}

	private static int count(int b, int x) {
		return (cycleShiftLeft(x, x)) ^ (cycleShiftLeft(x ^ b, x ^ b));
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

	private static void shuffleArray(int[] array) {
		int index;
		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			if (index != i) {
				array[index] ^= array[i];
				array[i] ^= array[index];
				array[index] ^= array[i];
			}
		}
	}
}
