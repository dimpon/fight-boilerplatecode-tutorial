package com.dimpon.tutorals.lombok.builders.sampleN_superbuilder;

import lombok.experimental.SuperBuilder;

/**
 * @author Dmitrii Ponomarev
 */
@SuperBuilder
public class TradeExtended extends Trade {
	private String approver;

	public static void main(String[] args) {
		Trade trade = TradeExtended.builder()
				.approver("approver")
				.currency("currency")
				.seller("seller")
				.buyer("buyer")
				.build();

	}
}
