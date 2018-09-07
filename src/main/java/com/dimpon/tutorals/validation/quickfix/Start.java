package com.dimpon.tutorals.validation.quickfix;

import com.dimpon.tutorals.validation.dto.Auto;
import lombok.Lombok;
import lombok.extern.slf4j.Slf4j;
import quickfix.field.OrdType;
import quickfix.field.Price;
import quickfix.fix44.NewOrderSingle;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class Start {

    public static void main(String[] args) {


            NewOrderSingle order = new NewOrderSingle();
            order.setField(new OrdType('2'));
            order.setField(new Price(1000.50));

            NewOrderSingleDelegate delegate = NewOrderSingleDelegate.of(order);

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

            Validator validator = factory.getValidator();

            Set<ConstraintViolation<NewOrderSingleDelegate>> violations = validator.validate(delegate);


            log.info("\n\n" +
                    violations.stream()
                            .map(v -> v.getMessage() + ": " + v.getInvalidValue())
                            .collect(Collectors.joining("\n")));





    }

}
