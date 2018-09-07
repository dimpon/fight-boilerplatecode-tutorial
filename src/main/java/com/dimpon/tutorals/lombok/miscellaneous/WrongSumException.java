package com.dimpon.tutorals.lombok.miscellaneous;

/**
 * @author Dmitrii Ponomarev
 */
public class WrongSumException extends Exception {

    public WrongSumException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.getMessage();
    }

}
