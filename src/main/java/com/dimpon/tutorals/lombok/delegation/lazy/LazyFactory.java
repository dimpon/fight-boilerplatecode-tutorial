package com.dimpon.tutorals.lombok.delegation.lazy;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

/**
 * @author Dmitrii Ponomarev
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LazyFactory<I> {

	private Class<I> interfaceClass;
	private Supplier<I> supplier;

	@SuppressWarnings("unchecked")
	private I getLazyObject() {
		return (I) Proxy.newProxyInstance(
				LazyFactory.class.getClassLoader(),
				new Class[] { interfaceClass },
				new LazyFactory.DynamicInvocationHandler());
	}

	public static <T> T getLazy(Class<T> interfaceClass, Supplier<T> supplier) {
		return new LazyFactory<T>(interfaceClass, supplier).getLazyObject();
	}

	private class DynamicInvocationHandler implements InvocationHandler {

		@Getter(lazy = true)
		private final I internalObject = supplier.get();

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return method.invoke(getInternalObject(), args);
		}
	}
}
