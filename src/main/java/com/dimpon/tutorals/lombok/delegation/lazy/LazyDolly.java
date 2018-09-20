package com.dimpon.tutorals.lombok.delegation.lazy;

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
        System.out.println("Hello1 I'm very lazy");
    }
}
