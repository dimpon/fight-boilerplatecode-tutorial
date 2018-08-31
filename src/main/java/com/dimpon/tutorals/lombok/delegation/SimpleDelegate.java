package com.dimpon.tutorals.lombok.delegation;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * @author Dmitrii Ponomarev
 */

//@AllArgsConstructor(onConstructor = @__(@Deprecated) )
@AllArgsConstructor
public class SimpleDelegate {

	interface AlloweMethods {
		public void forEach(Consumer action);
		int size();
	}

    interface OverridenMehods {
        int size();
    }

	@Delegate(excludes = OverridenMehods.class,types = AlloweMethods.class)
	private final Collection<String> element;


	public int size() {

		if (element.size() == 0)
			return 1000;

		return element.size();
	}

}
