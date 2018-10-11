package com.dimpon.tutorals.commands;

import lombok.RequiredArgsConstructor;

import java.security.PrivateKey;
import java.util.List;
import java.util.function.Function;

/**
 * @author Dmitrii Ponomarev
 */
public class LogStrategy {

	List<LogParam<?>> params;

	@RequiredArgsConstructor(staticName = "of")
	public static class LogParam<T> {
		private final String name;
		private final T value;
		private Function<T, String> converter = v -> name + "=" + v.toString();
	}
}
