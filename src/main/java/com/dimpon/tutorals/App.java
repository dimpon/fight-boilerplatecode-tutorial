package com.dimpon.tutorals;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

/**
 * Hello world!
 *
 */
@Slf4j
public class App 
{
    public static void main( String[] args )
    {




        log.info(String.format("xui voine %s ta ta ta %s.", "A", "B"));

        log.info(MessageFormat.format("xui voine {1} ta ta ta {0}", "A", "B"));
    }
}
