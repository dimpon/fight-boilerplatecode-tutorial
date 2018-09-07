package com.dimpon.tutorals.lombok.delegation;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start {

	public static void main(String[] args) {

		SimpleDelegate delegate = SimpleDelegate.of(new ArrayList<>());
		log.info("size = " + delegate.size());
		delegate.forEach(o -> {});

		Octopus o = new Octopus();
		Fisher f = new Fisher();

		OctopusDelegate delegate1 = OctopusDelegate.of(o,f);

		delegate1.head();
		delegate1.leg1();
		delegate1.catchIt(o);
		log.info(delegate1.iSeeOctopus());

		//delegate1.sayWhoIam();   //lombok is not able to generate method, it is ambiguous

	}
}
