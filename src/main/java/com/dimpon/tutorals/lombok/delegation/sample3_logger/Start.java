package com.dimpon.tutorals.lombok.delegation.sample3_logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

/**
 * @author Dmitrii Ponomarev
 */
//@Slf4j
public class Start {

	/*private static final LazyLogger LOG = LazyLogger.create((() ->
			LoggerFactory.getLogger(Start.class))
	);
*/

	private static final Logger LOG = LazyLoggerThreadSafe.getLogger(Start.class);

	private static final Logger LOGGY = LoggerFactory.getLogger(Start.class);
	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Start.class.getName());




	public static void main(String[] args) {
		LOGGER.log(Level.SEVERE,"sayHello");
	}
}
