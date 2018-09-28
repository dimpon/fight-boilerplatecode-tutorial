package com.dimpon.tutorals.lombok.delegation.sample3_logger;

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
public class LazyLoggerThreadSafe implements Logger {

	private static final Function<Class<?>, Logger> $function = LoggerFactory::getLogger;
	private final Class<?> clazz;

	@Getter(lazy = true, onMethod_ = { @Delegate }, value = AccessLevel.PRIVATE)
	private final Logger logger = createLogger();

	private Logger createLogger() {
		return $function.apply(clazz);
	}
}
