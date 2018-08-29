package com.dimpon.tutorals.lombok.valandvar;

import lombok.val;
import lombok.var;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Dmitrii Ponomarev
 */
public class VarValSample {

    public String example() {
        val example = new ArrayList<String>();
        example.add("Hello, World!");
        val foo = example.get(0);
        return foo.toLowerCase();
    }

    public void example2() {
        val map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        for (val entry : map.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }
    }

    public void example3() {
        var map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        for (val entry : map.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }

        map = null;
        map = new HashMap<>();
        map.put(4,"four");
    }

}

