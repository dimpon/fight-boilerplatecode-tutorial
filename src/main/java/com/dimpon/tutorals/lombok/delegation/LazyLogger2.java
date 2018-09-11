package com.dimpon.tutorals.lombok.delegation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * @author Dmitrii Ponomarev
 */
@RequiredArgsConstructor(staticName = "getLogger")
public class LazyLogger2 implements Logger {

	private static final Function<Class<?>, Logger> $function = LoggerFactory::getLogger;

	@Getter(lazy = true, onMethod = @__({ @Delegate }),value = AccessLevel.PRIVATE)
	private final Logger logger = createLogger();

	private final Class<?> clazz;

	private Logger createLogger() {
		return $function.apply(clazz);
	}

}
