package com.dimpon.tutorals.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.lang.annotation.Annotation;

/**
 * @author Dmitrii Ponomarev
 */
@Getter
@Setter
@Accessors(chain = true, fluent = true)
@RequiredArgsConstructor(staticName = "of")
@ToString
public class CommandImpl implements Command {

	private Class<? extends CommandGranular> value = null;
	private CommandElement[] elements = new CommandElement[0];
	private Class<? extends Annotation>[] annotations = new Class[0];
	private String[] params = new String[0];

	@Override
	public Class<Command> annotationType() {
		return Command.class;
	}
}
