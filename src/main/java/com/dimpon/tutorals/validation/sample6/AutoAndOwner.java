package com.dimpon.tutorals.validation.sample6;

import com.dimpon.tutorals.validation.sample1_1.Auto;
import com.dimpon.tutorals.validation.sample2.Owner;
import com.dimpon.tutorals.validation.sample6.validator.AutoAndOwnerTuple;
import org.apache.commons.lang3.tuple.Pair;

public interface AutoAndOwner {
	void validateWithTuples(Pair<Auto, Owner> pair);
}
