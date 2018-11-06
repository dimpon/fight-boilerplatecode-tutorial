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

	private Bird b = Watcher.<Bird> builder()
			// .function(bird -> {bird.fly(); return bird;})
			.original(new BirdImpl())
			.interfaceClass(Bird.class)
			.addListener("", Bird::eat)
			.addListener((proxy, args) -> log.info("...xxx 1"), bc -> bc.getMethods()[1])
			.addListener((proxy, args) -> log.info("...xxx 2"), bc -> bc.getDeclaredMethod("breed", int.class))
			// .addListener((proxy, args) -> {log.info("...fly");},"fly")
			.addListener((proxy, args) -> log.info("...xxx 3"), "eat", String.class)
			.build()
			.getProxy();
	



	private Bird bb = Watcher.<Bird> builder()
			.original(new BirdImpl())
            .interfaceClass(Bird.class)

			.addListener((proxy, args) -> {

			    log.info("*** eat:"+args[0]);

			}, bc -> bc.getDeclaredMethod("eat", String.class))

			.build()
			.getProxy();



	private void playWithBird() {

	    bb.eat("Corn");


	    b.eat("Rise");
		b.breed(3);
		b.fly();
		/*b.fly();
		b.eat("corn");
		b.breed(3);

		Optional.<String> empty();*/

	}

	Function<String, String> f = (a) -> "s";

	public static void main(String[] args) throws Exception {

		/*m1 = Bird.class.getDeclaredMethod("fly");

		log.info(m1.toString());

		Runtime.getRuntime().traceMethodCalls(true);*/

		Start s = new Start();
		s.playWithBird();
	}

}
