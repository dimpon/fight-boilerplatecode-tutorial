package com.dimpon.tutorals.lombok.builders.sample5_transformator;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.BiFunction;

import static com.dimpon.tutorals.lombok.builders.sample5_transformator.Transformator.TransformationSet.SET1;
import static com.dimpon.tutorals.lombok.builders.sample5_transformator.Transformator.TransformationSet.SET2;
import static com.dimpon.tutorals.lombok.builders.sample5_transformator.Transformator.TransformationSet.SET3;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start {

	private static final BiFunction<TradenIntention, FixData, TradenIntention> complexFunction = (iradenIntention, fixData) -> {

		if (fixData.params.size() == 3) {
			iradenIntention.bank("Citi");
		}

		return iradenIntention;
	};

	public static void main(String[] args) {

		//////////////////////////////////////////////

		Transformator<TradenIntention, FixData> transformator = Transformator.<TradenIntention, FixData> builder()
				.source(new FixData())
				/*
				 * .operation((iradenIntention, fixData) -> iradenIntention.currency(fixData.params.get("currency")))
				 * .operation((iradenIntention, fixData) -> iradenIntention.amount(fixData.params.get("amount")))
				 * .operation((iradenIntention, fixData) -> iradenIntention.bank(fixData.params.get("bank")))
				 * .operation(complexFunction)
				 */
				.build()
				.annFunction(SET1,(iradenIntention, fixData) -> iradenIntention.currency(fixData.params.get("currency")+"1"))
				.annFunction(SET1,(iradenIntention, fixData) -> iradenIntention.amount(fixData.params.get("amount")+"1"))
				.annFunction(SET1,(iradenIntention, fixData) -> iradenIntention.bank(fixData.params.get("bank")+"1"))

				.annFunction(SET2,(iradenIntention, fixData) -> iradenIntention.currency(fixData.params.get("currency")+"2"))
				.annFunction(SET2,(iradenIntention, fixData) -> iradenIntention.amount(fixData.params.get("amount")+"2"))
				.annFunction(SET2,(iradenIntention, fixData) -> iradenIntention.bank(fixData.params.get("bank")+"2"))

				.annFunction(SET3,(iradenIntention, fixData) -> iradenIntention.currency(fixData.params.get("currency")+"3"))
				.annFunction(SET3,(iradenIntention, fixData) -> iradenIntention.amount(fixData.params.get("amount")+"3"))
				.annFunction(SET3,(iradenIntention, fixData) -> iradenIntention.bank(fixData.params.get("bank")+"3"));


				//.transform(SET1, new TradenIntention());

		log.info(transformator.transform(SET1, new TradenIntention()).toString());
		log.info(transformator.transform(SET2, new TradenIntention()).toString());
		log.info(transformator.transform(SET3, new TradenIntention()).toString());


	}

	@Getter
	private static class FixData {
		Map<String, String> params = new HashMap<String, String>() {
			{
				put("currency", "EUR");
				put("amount", "1000000");
				put("bank", "Postbank");
			}
		};
	}

	@Data
	@Accessors(chain = true, fluent = true)
	private static class TradenIntention {
		String currency,
				amount,
				bank;
	}
}
