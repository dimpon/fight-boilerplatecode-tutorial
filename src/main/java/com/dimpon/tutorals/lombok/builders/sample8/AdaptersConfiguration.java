package com.dimpon.tutorals.lombok.builders.sample8;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * @author Dmitrii Ponomarev
 */
@Builder(builderMethodName = "of", buildMethodName = "create")
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@ToString
public class AdaptersConfiguration {

	@NonNull
	final String name, host;

	@NonNull
	Env environment;

	@Builder.Default
	int port = 8080;

	@Singular
	List<OneAdapter> adapters;

	enum Env {
		PROD, INT, DEV
	}

	/*
	 * public static class AdaptersConfigurationBuilder {
	 * public AdaptersConfiguration create() {
	 * //write your logic
	 * return new AdaptersConfiguration(this.myName, this.host, this.environment,this.port);
	 * }
	 * }
	 */
	@NoArgsConstructor(staticName = "of")
	@Accessors(chain = true, fluent = true)
	@FieldDefaults(level = AccessLevel.PRIVATE)
	@Setter
	@ToString
	public static class OneAdapter {
		@NonNull
		String name, counterparty, message;
		@NonNull
		MessageType type;

		enum MessageType {
			FIX, XML, SCV
		}
	}
}
