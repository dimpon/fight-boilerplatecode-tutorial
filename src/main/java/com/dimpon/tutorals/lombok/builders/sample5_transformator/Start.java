package com.dimpon.tutorals.lombok.builders.sample5_transformator;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.BiFunction;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start {

	private static final BiFunction<TradenIntention, FixData, TradenIntention> bankIsCiti = (ti, fd) -> ti.bank("Citi");




	public static void main(String[] args) {

		//////////////////////////////////////////////

		Transformer<FixData, TradenIntention> transformator = TransformerImpl.<FixData, TradenIntention> builder()
				.emptyResult(TradenIntention::new)
				.operation((ti, fd) -> ti.currency(fd.params.get("currency")))
				.operation((ti, fd) -> ti.amount(fd.params.get("amount")))
				.operation((ti, fd) -> ti.bank(fd.params.get("bank")))
				.operation((ti, fd) -> ti.bank(ti.bank + "_BANK"))
				.operation(bankIsCiti)
				.build();

		TradenIntention transform = transformator.transform(new FixData());

	}

	//@Getter
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
