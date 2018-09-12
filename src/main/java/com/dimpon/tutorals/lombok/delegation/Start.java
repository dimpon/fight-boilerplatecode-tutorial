package com.dimpon.tutorals.lombok.delegation;

import org.slf4j.Logger;

import java.util.ArrayList;

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


	public static void main(String[] args) {

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
