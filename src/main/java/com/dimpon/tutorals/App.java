package com.dimpon.tutorals;

import lombok.extern.slf4j.Slf4j;
import org.loguno.Loguno;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
@Slf4j
@Loguno.Logger
public class App {

	@Loguno
	private static void a() {
		b();
	}

	@Loguno
	private static void b() {
		c();
	}

	@Loguno
	private static void c() {

		/*
		 * StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		 * StackTraceElement stackTraceElement = stackTrace[0];
		 */

		throw new RuntimeException();
	}

	@Loguno
	public static void main(String[] args) {



		int logoN = "LOGON aa aaa [A]".compareToIgnoreCase("logo");

		// a();

		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m = p.matcher("Where Are You [{}:{}]? your have a [Shift] shift.");
		while (m.find()) {
			System.out.println(m.group());
		}

		Scanner sc = new Scanner("Where Are You [Employee Name]? your have a [Shift] shift..");
		for (String s; (s = sc.findWithinHorizon("(?<=\\[).*?(?=\\])", 0)) != null;) {
			System.out.println(s);
		}

		Scanner sc1 = new Scanner("<8>    BeginString     = FIX.4.2");
		for (String s; (s = sc1.findWithinHorizon("(?<=<).*?(?=>)", 0)) != null;) {
			System.out.println(s);
		}

		Pattern p1 = Pattern.compile("(?<=<).*?(?=>)");
		Matcher m1 = p1.matcher("<8>    BeginString     = FIX.4.2");
		while (m1.find()) {
			System.out.println("m1=" + m1.group());
		}


		//////////////




		String val = "4 (ISIN_NUMBER)";


		Pattern p2 = Pattern.compile("(?<=\\().*?(?=\\))");
		Matcher m2 = p2.matcher(val);
		while (m2.find()) {
			System.out.println("m3=" + m2.group());
		}


		// <8> BeginString = FIX.4.2

	}
}
