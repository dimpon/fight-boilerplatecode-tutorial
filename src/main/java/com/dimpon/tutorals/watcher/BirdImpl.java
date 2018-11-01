package com.dimpon.tutorals.watcher;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class BirdImpl implements Bird {
	@Override
	public void fly() {
		log.info("fly");
	}

	@Override
	public void eat(String food) {
		log.info("eat");
	}

	@Override
	public void breed(int eggs) {
		log.info("breed");

		throw new NullPointerException("all eggs are died");

	}

	public void kaka() {
	}
}
