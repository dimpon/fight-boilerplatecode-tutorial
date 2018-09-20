package com.dimpon.tutorals.lombok.delegation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * @author Dmitrii Ponomarev
 */
//@Slf4j
public class Start {

	/*private static final LazyLogger LOG = LazyLogger.of((() ->
			LoggerFactory.getLogger(Start.class))
	);
*/

	private static final Logger LOG = LazyLoggerThreadSafe.getLogger(Start.class);


	private static final Logger LOG1 = LoggerFactory.getLogger(Start.class);

	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Start.class.getName());

	public static void main(String[] args) {

		LazyStaticFieldThreadSafe<LazyStaticFieldThreadSafe.HelloDolly, LazyStaticFieldThreadSafe.HelloDollyImpl> factory = new LazyStaticFieldThreadSafe<LazyStaticFieldThreadSafe.HelloDolly, LazyStaticFieldThreadSafe.HelloDollyImpl>(){};

		Class actualClass = factory.getClass();
		ParameterizedType type = (ParameterizedType)actualClass.getGenericSuperclass();
		System.out.println(type); // java.util.ArrayList<java.lang.Float>
		Class parameter = (Class)type.getActualTypeArguments()[0];
		System.out.println(parameter);

		LOGGER.log(Level.SEVERE,"hello");

		SimpleDelegate delegate = SimpleDelegate.of(new ArrayList<>());
        LOG.info("size = " + delegate.size());
		delegate.forEach(o -> {
		});

		Octopus o = new Octopus();
		Fisher f = new Fisher();

		OctopusDelegate delegate1 = OctopusDelegate.of(o, f);

		delegate1.head();
		delegate1.leg1();
		delegate1.catchIt(o);
		LOG.info(delegate1.iSeeOctopus());

		// delegate1.sayWhoIam(); //lombok is not able to generate method, it is ambiguous

	}
}
