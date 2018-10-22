package com.dimpon.tutorals.watcher;

import com.dimpon.tutorals.lombok.delegation.multipurpose_lazy_factory.LazyFactory;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author Dmitrii Ponomarev
 */
@Builder
@Slf4j
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Watcher<I> {

	//private final I original;


	@Singular
	private Map<Method,Runnable> interceptors;

	/*@Singular
	private List<Function<? super Bird,  Bird>> functions;
*/
	/*public <B extends Bird> Watcher listen(Consumer<? extends B> property) {
		return this;
	}*/


	private Class<I> interfaceClass;
	private I original;

	@SuppressWarnings("unchecked")
	public I getProxy() {
		return (I) Proxy.newProxyInstance(
				LazyFactory.class.getClassLoader(),
				new Class[] { interfaceClass },
				new Watcher.DynamicInvocationHandler());
	}

	/*public static <T> T getLazy(Class<T> interfaceClass, Supplier<T> supplier) {
		return new Watcher<T>(interfaceClass, supplier).getProxy();
	}*/

	private class DynamicInvocationHandler implements InvocationHandler {

		//@Getter(lazy = true)
		//private final I realObject = supplier.get();

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

			interceptors.getOrDefault(method,() -> {}).run();

			return method.invoke(original, args);
		}
	}

}
