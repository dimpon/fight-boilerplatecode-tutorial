package com.dimpon.tutorals.lombok.delegation;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @author Dmitrii Ponomarev
 */
//@Slf4j
public class Start {

	private static final LogDelegator LOG = LogDelegator.of((() ->
			LoggerFactory.getLogger(Start.class))
	);



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
