package com.dimpon.tutorals.lombok.delegation.multipurpose_lazy_factory;

/**
 * @author Dmitrii Ponomarev
 */
public class HappyDolly implements Dolly {

	public HappyDolly() {
        System.out.println("...HappyDolly");
        System.out.println("...HappyDolly");
        System.out.println("...HappyDolly");
        System.out.println("...HappyDolly");
	}

	@Override
	public void hello() {
		System.out.println("Yo-hou!");
	}
}
