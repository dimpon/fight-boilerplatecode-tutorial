package com.dimpon.tutorals.lombok.delegation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * @author Dmitrii Ponomarev
 */

@AllArgsConstructor
public class SimpleDelegate {

	interface ForEach {
		public void forEach(Consumer action);
	}

    interface KeepSize {
        int size();
    }

	@Delegate(excludes = KeepSize.class)
	private final Collection<String> element;


	public int size() {

		if (element.size() == 0)
			return 1000;

		return element.size();
	}

}
