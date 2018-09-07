package com.dimpon.tutorals.lombok.delegation;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

/**
 * @author Dmitrii Ponomarev
 */
@RequiredArgsConstructor(staticName = "of")
public class OctopusDelegate {

    @Delegate
    private final Octopus octopus;


    @Delegate
    private final Fisher fisher;

}
