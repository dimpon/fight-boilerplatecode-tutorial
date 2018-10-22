package com.dimpon.tutorals.watcher;

import com.dimpon.tutorals.lombok.delegation.multipurpose_lazy_factory.LazyFactory;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Dmitrii Ponomarev
 */
@Builder
@Slf4j
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Watcher<I> {

    //private final I original;

    public static class WatcherBuilder<I> {


        private Map<Method, ListenerCommand<I>> interceptors = new LinkedHashMap<>();

        public <P> WatcherBuilder<I> addListener(P val, BiConsumer<I, P> function) {

            return this;
        }


        //private Map<Method, ListenerCommand<I>> interceptors = new LinkedHashMap<>();

        public WatcherBuilder<I> addListener(ListenerCommand<I> command, Method method) {
            WatcherBuilder.this.interceptors.put(method, command);
            return this;
        }

        @SneakyThrows
        public WatcherBuilder<I> addListener(ListenerCommand<I> command, String name, Class<?>... params) {

            Method method = interfaceClass.getDeclaredMethod(name, params);

            WatcherBuilder.this.interceptors.put(method, command);

            return this;
        }
    }


    //@Singular
    private final Map<Method, ListenerCommand<I>> interceptors;

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
                new Class[]{interfaceClass},
                new Watcher.DynamicInvocationHandler());
    }

	/*public static <T> T getLazy(Class<T> interfaceClass, Supplier<T> supplier) {
		return new Watcher<T>(interfaceClass, supplier).getProxy();
	}*/


    private class DynamicInvocationHandler implements InvocationHandler {

        //@Getter(lazy = true)
        //private final I realObject = supplier.get();

        @SuppressWarnings("unchecked")
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            interceptors.getOrDefault(method, (a, b) -> {
            }).fire((I) proxy, args);
            return method.invoke(original, args);
        }
    }

    @FunctionalInterface
    public interface ListenerCommand<I> {
        void fire(I proxy, Object[] args);
    }

}
