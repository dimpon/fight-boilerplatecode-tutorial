package com.dimpon.tutorals.visitor;

/**
 * @author Dmitrii Ponomarev
 */
public class Start {
    public static void main(String[] args) {
        ZOne.of()
                .three(ZThree.of())
                .two1(ZTwo.of())
                .two2(ZTwo.of())
                .accept(new ZVisitor());
    }
}
