package com.dimpon.tutorals.visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class ZVisitor {

	public void visit(ZOne element) {
		log.info(element.toString());
	}

	public void visit(ZTwo element) {
		log.info(element.toString());
	}

	public void visit(ZThree element) {
		log.info(element.toString());
	}

    public void visit(ZAcceptor element) {
        log.info(element.toString());
    }

}
