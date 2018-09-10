package com.dimpon.tutorals.lombok.delegation;

import jdk.nashorn.internal.runtime.options.Option;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.slf4j.Logger;


import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Dmitrii Ponomarev
 */
@RequiredArgsConstructor(staticName = "of")
public class LogDelegator {

    private final Supplier<Logger> supplier;
    private final Optional<Logger> $logger = Optional.empty();

    @Delegate
    private Logger getLogger(){
        return $logger.orElseGet(supplier);
    }

}
