package com.dimpon.tutorals;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
@Slf4j
public class App 
{
    public static void main( String[] args )
    {



        LinkedList<String> classes = new LinkedList<>();

        classes.add("a1");
        classes.add("a2");
        classes.add("a3");
        classes.add("a4");

        classes.removeLast();
        classes.removeLast();
        classes.removeLast();

        //String s = "Hello my name is Joe";
        //Matcher m = Pattern.compile("Hello my name is ([\\w]*)").matcher(s);


       /* String s = "Hello my namef is [xxx] fdfddfd";
        Matcher m = Pattern.compile("([\\w])").matcher(s);

        if(m.matches())
        {
            log.info("Name entered: " + m.group(1));
        }*/

        Pattern p = Pattern.compile("\\[(.*?)\\]");
        Matcher m = p.matcher("Where Are You [{}:{}]? your have a [Shift] shift.");
        while(m.find()) {
            System.out.println(m.group());
        }

        Scanner sc = new Scanner("Where Are You [Employee Name]? your have a [Shift] shift..");
        for (String s; (s = sc.findWithinHorizon("(?<=\\[).*?(?=\\])", 0)) != null;) {
            System.out.println(s);
        }


        //log.info(String.format("xui voine %s ta ta ta %s.", "A", "B"));

        //log.info(MessageFormat.format("xui voine {1} ta ta ta {0}", "A", "B"));




    }
}
