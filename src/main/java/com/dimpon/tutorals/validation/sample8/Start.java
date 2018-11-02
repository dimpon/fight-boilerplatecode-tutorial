package com.dimpon.tutorals.validation.sample8;

import com.dimpon.tutorals.validation.PrintUtils;
import com.dimpon.tutorals.validation.sample4.Producer;
import com.dimpon.tutorals.validation.sample8.dto.ZAuto;
import com.dimpon.tutorals.validation.sample8.dto.ZOwner;
import com.dimpon.tutorals.validation.sample8.proxy.ValidationWrapper;
import lombok.SneakyThrows;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import javax.validation.groups.Default;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author Dmitrii Ponomarev
 */
public class Start {

	@SneakyThrows
	public static void main(String[] args) {

		ZAuto a = ZAuto.of().broken(true);
		ZOwner o = ZOwner.of().drunk(true);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		ExecutableValidator executableValidator = factory.getValidator().forExecutables();

		Method method = ServiceX.class.getDeclaredMethod("validateAutoAndOwnerWithCrossParameterConstraint", ZAuto.class, ZOwner.class);
		// Object[] params = { 1 };

		ServiceX service = new ServiceXIml();
		Object[] params = { a, o };

		Set<ConstraintViolation<ServiceX>> constraintViolationsM = executableValidator
				.validateParameters(service, method, params);

		PrintUtils.print(constraintViolationsM);


		/////////

		ServiceX s = ValidationWrapper.<ServiceX> builder()
                .interfaceClass(ServiceX.class)
                .original(new ServiceXIml())
                .build()
                .getProxy();

        try {
            s.validateAutoAndOwnerWithCrossParameterConstraint(a,o);

            //s.getValue("a");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
