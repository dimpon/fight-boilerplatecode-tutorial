package com.dimpon.tutorals.visitor;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Dmitrii Ponomarev
 */
@ToString
@RequiredArgsConstructor(staticName = "of")
public class ZThree implements ZAcceptor {
    @Override
    public void accept(ZVisitor visitor) {
        visitor.visit(this);
    }
}
