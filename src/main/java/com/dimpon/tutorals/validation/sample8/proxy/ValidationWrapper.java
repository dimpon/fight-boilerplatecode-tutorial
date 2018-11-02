package com.dimpon.tutorals.validation.sample8.proxy;

import com.dimpon.tutorals.lombok.delegation.multipurpose_lazy_factory.LazyFactory;
import lombok.Builder;

import javax.validation.*;
import javax.validation.executable.ExecutableValidator;
import javax.validation.groups.Default;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Dmitrii Ponomarev
 */
@Builder
public class ValidationWrapper<I> {

	private Class<I> interfaceClass;

	private I original;

    private ValidatorFactory $factory = Validation.buildDefaultValidatorFactory();
    private Validator $validator = $factory.getValidator();
	private ExecutableValidator $executableValidator = $validator.forExecutables();

	@SuppressWarnings("unchecked")
	public I getProxy() {
		return (I) Proxy.newProxyInstance(
				LazyFactory.class.getClassLoader(),
				new Class[] { interfaceClass },
				new ValidationWrapper.DynamicInvocationHandler());
	}

	private class DynamicInvocationHandler implements InvocationHandler {

		@SuppressWarnings("unchecked")
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

			try {

				Set<ConstraintViolation<I>> constraintViolationsP = $executableValidator
						.validateParameters(original, method, args);

                Set<ConstraintViolation<Object>> collect = Arrays.stream(args).map(o -> $validator.validate(o)).flatMap(Collection::stream).collect(Collectors.toSet());

                Set<ConstraintViolation<? super I>> joined = new HashSet<>();
                joined.addAll(constraintViolationsP);
                joined.addAll(collect);

                Object result=null;

                if(joined.size()==0) {
                    result = method.invoke(original, args);
                    Set<ConstraintViolation<I>> constraintViolationsR = $executableValidator
                            .validateReturnValue(original, method, result, Default.class);
                    joined.addAll(constraintViolationsR);
                }

                if(joined.size()>0)
                    throw new ConstraintViolationException(joined);
                else
				return result;
			} catch (InvocationTargetException e) {
				throw e.getCause();
			}
		}
	}

}
