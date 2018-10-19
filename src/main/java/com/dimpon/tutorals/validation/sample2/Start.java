package com.dimpon.tutorals.validation.sample2;

import com.dimpon.tutorals.validation.PrintUtils;
import lombok.SneakyThrows;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * @author Dmitrii Ponomarev
 */

public class Start {

    @SneakyThrows
    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        Set<ConstraintViolation<Producer>> constraintViolations = factory.getValidator().forExecutables()
                .validateConstructorParameters(Producer.class.getConstructor(String.class), new String[] { "s" }, Default.class);

        PrintUtils.print(constraintViolations);

    }
}
