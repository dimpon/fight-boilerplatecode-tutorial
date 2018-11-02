package com.dimpon.tutorals.commands;

/**
 * @author Dmitrii Ponomarev
 */
public class NewHorizont {

    private Integer intValue = null;

    private int getVal(){
        return (Integer)this.intValue;
    }

    public static void main(String[] args) {

        NewHorizont nh = new NewHorizont();
        int val = nh.getVal();
        System.out.printf(""+val);

        Command command = CommandImpl.of().params(new String[]{"a","b","c"});


    }
}
