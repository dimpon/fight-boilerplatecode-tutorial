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

	    /*StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[0];*/

	    throw new RuntimeException();
    }

	@Loguno
	public static void main(String[] args) {

	    a();


		/*Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m = p.matcher("Where Are You [{}:{}]? your have a [Shift] shift.");
		while (m.find()) {
			System.out.println(m.group());
		}

		Scanner sc = new Scanner("Where Are You [Employee Name]? your have a [Shift] shift..");
		for (String s; (s = sc.findWithinHorizon("(?<=\\[).*?(?=\\])", 0)) != null;) {
			System.out.println(s);
		}*/


	}
}
