package com.dimpon.tutorals.lombok.builders.sample8;

import com.dimpon.tutorals.lombok.builders.sample8.AdaptersConfiguration.OneAdapter;
import com.dimpon.tutorals.lombok.builders.sample8.AdaptersConfiguration.OneAdapter.MessageType;
import lombok.extern.slf4j.Slf4j;
import static com.dimpon.tutorals.lombok.builders.sample8.AdaptersConfiguration.Env.*;
import static com.dimpon.tutorals.lombok.builders.sample8.AdaptersConfiguration.OneAdapter.MessageType.*;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start {
	public static void main(String[] args) {

		AdaptersConfiguration conf = AdaptersConfiguration.of()
				.name("My adapters")
				.host("edmz04")
				.environment(INT)
				.adapter(OneAdapter.of()
                        .counterparty("Citi")
                        .name("Citi_ti")
                        .type(FIX)
                        .message("lwefwjekjewflkw"))
                .adapter(OneAdapter.of()
                        .counterparty("DeutcheBank")
                        .name("DeutcheBank_ti")
                        .type(SCV)
                        .message("lwefwjekjewflkw"))
                .adapter(OneAdapter.of()
                        .counterparty("Sberbank")
                        .name("Sberbank_ti")
                        .type(XML)
                        .message("lwefwjekjewflkw"))
				.create();

		log.info(conf.toString());

	}
}
