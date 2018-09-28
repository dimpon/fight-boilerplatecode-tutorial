package com.dimpon.tutorals.lombok.delegation.sample1;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * @author Dmitrii Ponomarev
 */

@Slf4j
// @AllArgsConstructor(onConstructor = @__(@Deprecated) )
// @AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class SimpleDelegate {

	interface AllowedMethods {
		void forEach(Consumer action);

		int size();
	}

	interface OverriddenMehods {
		int size();
	}


	@Delegate(excludes = OverriddenMehods.class, types = AllowedMethods.class)
	private final Collection<String> element;

	public int size() {
		if (element.size() == 0)
			return 1000;

		return element.size();
	}

	public static void main(String[] args) {
		SimpleDelegate delegate = SimpleDelegate.of(new ArrayList<>());

		log.info("size = " + delegate.size());
		delegate.forEach(o -> {
		});
	}

}
