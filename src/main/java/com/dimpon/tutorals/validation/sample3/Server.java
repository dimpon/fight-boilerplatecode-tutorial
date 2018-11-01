package com.dimpon.tutorals.validation.sample3;

import com.dimpon.tutorals.validation.PrintUtils;
import com.dimpon.tutorals.validation.sample3.validator.Equals;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author Dmitrii Ponomarev
 */
@Getter
@Setter
@Accessors(chain = true,fluent = true)
@RequiredArgsConstructor(staticName = "of")
public class Server {

    @Equals("PROD")
    private String environment;

    @Equals("360T")
    private String name;


    public static void main(String[] args) {

        Server server = Server.of().environment("INT").name("Dmitrii's pet");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Server>> constraintViolations = validator.validate(server);

        PrintUtils.print(constraintViolations);

    }

}
