package com.dimpon.tutorals.lambdas;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * @author Dmitrii Ponomarev
 */
public class Lambda1 {

	private  int num;



	public Lambda1(int num) {
		this.num = num;
	}

	private String doIt(final String name) {
		AtomicInteger x = new AtomicInteger((new Random()).nextInt());

		//Supplier<String> s = () -> myName + num + x.getAndIncrement();

		Supplier<String> s = () -> Integer.toString(num++);

		String s1 = s.get();
		System.out.printf(s1);
		return s1;
	}

	public static void main(String[] args) {
		Lambda1 lambda1 = new Lambda1(5);
		lambda1.doIt("zzz");
		lambda1.doIt("ttt");
	}
}
