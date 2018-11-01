package com.dimpon.tutorals.validation.sample7;

import com.dimpon.tutorals.validation.PrintUtils;
import com.dimpon.tutorals.validation.sample1_1.validator.Equals;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @author Dmitrii Ponomarev
 */
@Getter
@Setter
@Accessors(chain = true, fluent = true)
@RequiredArgsConstructor(staticName = "of")
@ToString
public class Employee {

    @NotBlank(message = "Name is required")
    private String name;

    @Equals("360T")
    private Profile profile;




    public static void main(String[] args) {

        Employee employee = Employee.of()
                .name("Dmitry")
                .profile(new Profile("Luxoft"));


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);

        PrintUtils.print(constraintViolations);

    }
}
