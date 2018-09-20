package com.dimpon.tutorals.lombok.delegation.lazy;

/**
 * @author Dmitrii Ponomarev
 */
public class Start {

	private static final Dolly lazyObject1 = LazyFactory.getLazy(Dolly.class, HappyDolly::new);
    private static final Dolly lazyObject2 = LazyFactory.getLazy(Dolly.class, LazyDolly::new);

	public static void main(String[] args) {

		System.out.println("Both dollyes are here!");

		lazyObject1.hello();

        lazyObject2.hello();

	}

}
