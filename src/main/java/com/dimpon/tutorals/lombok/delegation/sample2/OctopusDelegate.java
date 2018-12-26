package com.dimpon.tutorals.lombok.delegation.sample2;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class OctopusDelegate {


    private final Octopus octopus;

    @Delegate
    private Octopus getOctopus() {
        log.info("octopus get..");
        return octopus;
    }

    @Delegate
    private final Fisher fisher;

}


@Slf4j
class Starter{

    public static void main(String[] args) {
        Octopus o = new Octopus();
        Fisher f = new Fisher();

        OctopusDelegate delegate = OctopusDelegate.of(o, f);

        delegate.head();
        delegate.leg1();
        delegate.leg2();
        delegate.leg3();



        log.info(delegate.iSeeOctopus());
        log.info(delegate.sayWhoIam());
    }
}