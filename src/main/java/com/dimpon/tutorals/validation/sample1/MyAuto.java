package com.dimpon.tutorals.validation.sample1;

import com.dimpon.tutorals.validation.PrintUtils;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Dmitrii Ponomarev
 */
@Getter
@Setter
@Accessors(chain = true, fluent = true)
@RequiredArgsConstructor(staticName = "of")
@ToString
public class MyAuto {

	@Size(max = 17, min = 17, message = "Vin must have 17 symbols")
	private String vin;

	@NotNull(message = "Mileage is required")
	private Long mileage;

	@Size(min = 5, message = "Must contain at lest 5 PR numbers")
	private List<@NotBlank(message = "PR numbers must not be blank") String> prNumbers;






	public static void main(String[] args) {

        List<String> pr = Arrays.asList("a", "b", "b", "");

        MyAuto myAuto = MyAuto.of()
                .mileage(10000L)
                .vin("1234567890ABCDEFG")
                .prNumbers(pr);


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<MyAuto>> constraintViolations = validator.validate(myAuto);

        PrintUtils.print(constraintViolations);
        
    }

}
