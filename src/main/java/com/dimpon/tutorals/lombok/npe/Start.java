package com.dimpon.tutorals.lombok.npe;

/**
 * @author Dmitrii Ponomarev
 */
public class Start {

    public static void main(String[] args) {

        NonNullDto dto = new NonNullDto();
        String name = dto.getName();

        //dto.pass(null);
        //dto.setMyName(null);


    }
}
