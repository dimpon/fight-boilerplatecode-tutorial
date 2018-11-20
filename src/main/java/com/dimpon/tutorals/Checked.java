package com.dimpon.tutorals;

import java.sql.SQLException;

/**
 * @author Dmitrii Ponomarev
 */
public class Checked {

        // no throws
        public static void main(String[] args) {


            String s = "dsdsd dsds dsUSD,fdf\n\rdf SPEC ffgffg";

            String n = s.replaceAll("[\\n,\\r]","");

                    //.replaceAll("(\\r)","");

            // ",(.*)SPEC"



            doThrow(new SQLException());
        }

        @SuppressWarnings("unchecked")
        private static <E extends Exception> void doThrow(Exception e) throws E {
            throw (E) e;
        }
}
