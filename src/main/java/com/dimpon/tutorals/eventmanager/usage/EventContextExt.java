package com.dimpon.tutorals.eventmanager.usage;

import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * @author Dmitrii Ponomarev
 */
@Value
@RequiredArgsConstructor(staticName = "of")
public class EventContextExt {
    final private String name;
}
