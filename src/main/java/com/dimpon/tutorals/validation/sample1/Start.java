package com.dimpon.tutorals.validation.sample1;

import com.dimpon.tutorals.validation.PrintUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author Dmitrii Ponomarev
 */
public class Start {

    public static void main(String[] args) {
        Auto auto = Auto.of();
        Owner owner = Owner.of().age(23).name("Dmitrii").drunk(true);
        OwnerAndAutoPair pair = new OwnerAndAutoPair(owner, auto);



        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<OwnerAndAutoPair>> constraintViolations = validator.validate(pair);

        PrintUtils.print(constraintViolations);
    }
}
