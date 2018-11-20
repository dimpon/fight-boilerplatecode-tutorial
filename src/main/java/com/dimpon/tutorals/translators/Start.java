package com.dimpon.tutorals.translators;

import com.dimpon.tutorals.validation.sample1_1.Auto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Map;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start {

	@Param(XTranslatorImpl.class)
	private static Auto a = Auto.of().mileage(12L).vin("ABC");

	public static void main(String[] args) throws Exception {

		/*
		 * log.info(""+(24^5));
		 * 
		 * log.info(""+(29^5));
		 * 
		 * log.info(""+(24^29));
		 */

		int[] all = new int[] { 1, 22, 3, 4, 5, 16, 27, 8, 1, 22, 3, 4, 5, 16, 27, 8 };

		/*
		 * int res = 0;
		 * 
		 * log.info("" + (2 ^ 7));
		 * 
		 * for (int i = 0; i < all.length; i++) {
		 * int i1 = all[i];
		 * res = res + i1;
		 * }
		 * 
		 * 
		 * int rr = res & ~res;
		 * 
		 * 
		 * for (int i = 0; i < all.length; i++) {
		 * int i1 = all[i];
		 * res = res ^ (i1 + i * i);
		 * }
		 * 
		 * log.info("xor=" + (2 ^ 7));
		 * 
		 * log.info("xor=" + ((2 ^ 7) ^ 7));
		 */

		AtomicInteger i = new AtomicInteger(1);

		int xorInde = IntStream.of(all).reduce((left, right) -> left ^ (right ^ i.getAndIncrement())).getAsInt();


        int xorInde2 = IntStream.of(all).  reduce((left, right) -> left ^ (right ^ i.getAndIncrement())).getAsInt();

		int xor = IntStream.of(all).reduce((left, right) -> left ^ right).getAsInt();



		log.info(Integer.toBinaryString(8 | 0b1000000));
		log.info(Integer.toBinaryString(8 >> 1 | 0b1000000));
		log.info(Integer.toBinaryString(8 >> 2 | 0b1000000));
		log.info(Integer.toBinaryString(8 >> 3 | 0b1000000));

		log.info("8=" + 48);
		log.info("8=" + (48 >> 1));
		log.info("8=" + (48 >> 2));
		log.info("8=" + (48 >> 3));

		MDC.put("auto", "some value a");

		Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();

		String auto = MDC.get("auto");

		Param a = Start.class.getDeclaredField("a").getAnnotation(Param.class);

		Class<? extends XTranslator> aClass = a.value()[0];

		XTranslator xTranslator = aClass.newInstance();

		XTranslator xTranslator1 = XTranslatorImpl.class.newInstance();

		Start start = new Start();

		String translate = xTranslator.translate(start.a);

		log.info(MDC.get("a") + translate);

		MDC.remove("a");

	}
}
