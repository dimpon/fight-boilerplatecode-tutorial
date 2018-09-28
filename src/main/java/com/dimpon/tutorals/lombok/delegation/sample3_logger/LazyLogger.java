package com.dimpon.tutorals.lombok.delegation.sample3_logger;

import jdk.nashorn.internal.runtime.options.Option;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Dmitrii Ponomarev
 */
@AllArgsConstructor(staticName = "getLogger")
public class LazyLogger implements Logger {

	private final static Function<Class<?>, Logger> $function = LoggerFactory::getLogger;
	private Logger $logger = null;
	private final Class<?> clazz;

	@Delegate
	private Logger getLogger() {
		if ($logger == null)
			$logger = $function.apply(clazz);
		return $logger;
	}


}




