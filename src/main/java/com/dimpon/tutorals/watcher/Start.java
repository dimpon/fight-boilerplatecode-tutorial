package com.dimpon.tutorals.watcher;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start {


    static Method m1;

	private Bird b = Watcher.<Bird>builder()
            //.function(bird -> {bird.fly(); return bird;})
            .original(new BirdImpl())
            .interfaceClass(Bird.class)
            .interceptor(m1,(proxy, args) -> {log.info("intercept !!!");})
            .build()
            .getProxy();





	private void playWithBird(){
	    b.fly();
	    b.eat("corn");
	    b.breed(3);

        Optional.<String>empty();

    }

    Function<String,String> f = (a)-> "s";

    public static void main(String[] args) throws Exception {

        m1 = Bird.class.getDeclaredMethod("fly");

        log.info(m1.toString());

        Runtime.getRuntime().traceMethodCalls(true);

        Start s = new Start();
        s.playWithBird();
    }

}
