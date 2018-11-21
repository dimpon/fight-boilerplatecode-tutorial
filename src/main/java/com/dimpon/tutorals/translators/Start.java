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

		int SIZE = 40;

		// fill the random int array of SIZE
		int[] values = new int[SIZE];

		for (int i = 0; i < SIZE - 2; i = i + 2) {
			int s = random.nextInt(3000);
			values[i] = s;
			values[i + 1] = s;
		}

		// generate 2 unique elements (the check if they are already in the array must be added)
		int X = random.nextInt(3000);
		int Y = random.nextInt(3000);

		log.info("X=" + X);
		log.info("Y=" + Y);
		values[SIZE - 2] = X;
		values[SIZE - 1] = Y;

		// shuffle array
		shuffleArray(values);

		// find 2 unique elemens and print
		findAndPrint(values);
	}

	/**
	 * basically we know 2 values, a and b:
	 *
	 * x^y = b
	 *
	 * (x << x)^(y << y) = a
	 *
	 * knowing this we can find x and y
	 */
	private static void findAndPrint(int[] all) {

		int a = 0;
		int b = 0;

		for (int u : all) {
			a = a ^ (cycleShiftLeft(u, u));
			b = b ^ u;
		}

		log.info("xorWithShift=" + a);
		log.info("xor=" + b);

		int x = 0;

		// the formula for finding x:
		// x ^ (b ^ x << b ^ x >> x) = a >> x
		for (;;) {
			x++;
			int l = left(b, x);
			int r = right(a, x);
			if (l == r)
				break;
		}

		log.info("X=" + x);
		log.info("Y=" + (b ^ x));
	}

	private static int right(int a, int x) {
		return cycleShiftRight(a, x);
	}

	private static int left(int b, int x) {
		return x ^ (cycleShiftRight(cycleShiftLeft(b ^ x, b ^ x), x));
	}

	// shift <=32
	private static int cycleShiftLeft(int val, int shift) {
		int i = shift % 32;// real shift
		int a1 = val >>> 32 - i;
		int a2 = val << i;
		return a1 | a2;
	}

	private static int cycleShiftRight(int val, int shift) {
		int i = shift % 32;// real shift
		int a1 = val << 32 - i;
		int a2 = val >>> i;
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
