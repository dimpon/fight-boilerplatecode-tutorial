package com.dimpon.tutorals.lombok.delegation.multipurpose_lazy_factory;

/**
 * @author Dmitrii Ponomarev
 */
public class LazyDolly implements Dolly {



    public LazyDolly() {
        System.out.println("...LazyDolly");
        System.out.println("...LazyDolly");
        System.out.println("...LazyDolly");
        System.out.println("...LazyDolly");
    }

    @Override
    public void hello() {
        System.out.println("Hello1 I'm very multipurpose_lazy_factory");
    }
}
