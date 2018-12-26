package com.dimpon.tutorals;

import jdk.internal.dynalink.CallSiteDescriptor;
import lombok.extern.slf4j.Slf4j;

import java.lang.invoke.*;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Checked {

	// no throws
	public static void main(String[] args) throws Throwable {

	    Checked c = new Checked();

		MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();

		MethodType mt = MethodType.methodType(List.class, Object[].class);

        MethodHandle createList = publicLookup.findVirtual(Checked.class, "createList", mt);

        Object invoke = createList.invoke(c, "a", "b", "c");


		CallSite s;

        //LambdaMetafactory

        // doThrow(new SQLException());
	}

	public List createList(Object... elements) {
	    log.info("createList...");
		return Stream.of(elements).collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	private static <E extends Exception> void doThrow(Exception e) throws E {
		throw (E) e;
	}
}
