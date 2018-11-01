package com.dimpon.tutorals.validation.sample5;

import com.dimpon.tutorals.validation.PrintUtils;
import com.dimpon.tutorals.validation.sample1_1.Auto;
import com.dimpon.tutorals.validation.sample2.Owner;
import com.dimpon.tutorals.validation.sample6.validator.AutoAndOwnerTuple;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start {

	public static void main(String[] args) {

		XAuto auto = XAuto.of().mileage(1000L).broken(true);
		XOwner owner = XOwner.of().age(23).drunk(true);

		OwnerAndAutoPair pair = new OwnerAndAutoPair(owner, auto);


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<OwnerAndAutoPair>> constraintViolations = validator.validate(pair);

        PrintUtils.print(constraintViolations);



	}




}
