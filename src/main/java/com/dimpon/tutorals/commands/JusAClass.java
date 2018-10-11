package com.dimpon.tutorals.commands;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(value = CommandGo.class, elements = {
		@CommandElement("a"),
		@CommandElement("b")
}, annotations = {
		Override.class,
		Deprecated.class,
		CommandElement.class
})
@Command(value = CommandGo.class, elements = {
		@CommandElement({ "a", "b" }),
		@CommandElement("b")
})
@Command(value = CommandGo.class, elements = {
		@CommandElement("a"),
		@CommandElement("b")
})

@Command.GoFaraway
@Bird
public class JusAClass {

	@Bird
	private static final Logger LOG = LoggerFactory.getLogger(JusAClass.class);

	@Bird
	void me(@Bird int a) throws @Bird Exception {

		@Bird
		Object o = new @Bird Object();

		o = new @Bird Object();

		if (o == null)
			new @Bird Object();


		@Bird
		List<@Bird String> li;

		if (true) {
			// throw new @Bird Exception();
			@Bird
			Exception e = new Exception();
			throw e;
		}

		@Bird({ "a", "b", "c" })
		Object s;

		@Bird({ "a", "b", "c", "o.toString()" })
		boolean xxx = (o == null);

		if (isDo()) {

		} else {

		}

		Date dd = new Date();

		try {
			Date.parse("sdfdf");
		} catch (@Bird Exception e) {
			e.printStackTrace();
		}
	}

	@Bird
	private boolean isDo() {
		return true;
	}

	@Bird
	private static void isDoDo() {

	}

}
