package com.dimpon.tutorals.lombok.delegation;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start {

	public static void main(String[] args) {

		SimpleDelegate delegate = new SimpleDelegate(new ArrayList<>());

		log.info("size = " + delegate.size());

	}
}
