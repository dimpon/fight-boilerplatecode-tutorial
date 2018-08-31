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

	}
}
