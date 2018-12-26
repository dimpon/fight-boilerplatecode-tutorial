package com.dimpon.tutorals.methodannprocessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Function;

import com.dimpon.tutorals.lombok.delegation.multipurpose_lazy_factory.LazyFactory;

import lombok.Builder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmitrii Ponomarev
 */
@Builder
@Slf4j
// @AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AnnotationOperator<O extends I, A extends Annotation, I> {

	private FunctionWithExceptions<O, A> operation;

	private Class<I> interfaceClass;
	private Class<A> annotationClass;
	private O original;

	@SuppressWarnings("unchecked")
	public I getProxy() {
		return (I) Proxy.newProxyInstance(
				LazyFactory.class.getClassLoader(),
				new Class[] { interfaceClass },
				new AnnotationOperator.DynamicInvocationHandler());
	}

	private class DynamicInvocationHandler implements InvocationHandler {

		@SuppressWarnings("unchecked")
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

			A annFromInterface = method.getAnnotation(annotationClass);

			Method declaredMethod = original.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());

			A annFromClass = declaredMethod.getAnnotation(annotationClass);

			operation.apply(original, (annFromInterface != null) ? annFromInterface : annFromClass);

			try {
				return method.invoke(AnnotationOperator.this.original, args);
			} catch (InvocationTargetException e) {
				throw  e.getTargetException();
			}
		}
	}

	@FunctionalInterface
	public interface FunctionWithExceptions<T, A extends Annotation> {
		void apply(T t, A a) throws Throwable;
	}
}
