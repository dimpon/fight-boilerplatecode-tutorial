package com.dimpon.tutorals.validation.sample6;

import com.dimpon.tutorals.validation.sample1_1.Auto;
import com.dimpon.tutorals.validation.sample2.Owner;
import com.dimpon.tutorals.validation.sample6.validator.AutoAndOwnerTuple;
import com.dimpon.tutorals.watcher.Watcher;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start implements AutoAndOwner {

	public static void main(String[] args) {

		YAuto a = YAuto.of().broken(false);

		YOwner o = YOwner.of().drunk(true).age(28);


		Start s = new Start();


	}

	public void validateWithTuples(@AutoAndOwnerTuple Pair<Auto, Owner> pair) {
		log.info(pair.getLeft().toString() + pair.getRight().toString());
	}

}
