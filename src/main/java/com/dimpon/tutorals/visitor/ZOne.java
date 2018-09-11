package com.dimpon.tutorals.visitor;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Dmitrii Ponomarev
 */
@ToString(exclude = {"two1","two2","three"})
@Setter
@Accessors(chain = true, fluent = true)
@RequiredArgsConstructor(staticName = "of")
public class ZOne implements ZAcceptor {

	private ZTwo two1;
	private ZTwo two2;
	private ZThree three;

	@Override
	public void accept(ZVisitor visitor) {
		visitor.visit(this);
		two1.accept(visitor);
        two2.accept(visitor);
        three.accept(visitor);
	}
}
