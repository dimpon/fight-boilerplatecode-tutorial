package com.dimpon.tutorals.lombok.delegation;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * @author Dmitrii Ponomarev
 */

//@AllArgsConstructor(onConstructor = @__(@Deprecated) )
//@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class SimpleDelegate {

	interface AllowedMethods {
		 void forEach(Consumer action);
		int size();
	}

    interface OverriddenMehods {
        int size();
    }

	@Delegate(excludes = OverriddenMehods.class,types = AllowedMethods.class)
	private final Collection<String> element;


	public int size() {

		if (element.size() == 0)
			return 1000;

		return element.size();
	}

}
