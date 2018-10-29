package com.dimpon.tutorals.watcher;

import com.dimpon.tutorals.lombok.delegation.multipurpose_lazy_factory.LazyFactory;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Dmitrii Ponomarev
 */
@Builder
@Slf4j
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Watcher<I> {

    public static class WatcherBuilder<I> {


        private Map<Method, ListenerCommand<I>> interceptors = new LinkedHashMap<>();
		private Class<I> interfaceClass;

        public <P> WatcherBuilder<I> addListener(P val, BiConsumer<I, P> function) {

            return this;
        }

        public WatcherBuilder<I> addListener(ListenerCommand<I> command,FunctionWithExceptions<Class<I>,Method> method) {
            WatcherBuilder.this.interceptors.put(method.apply(WatcherBuilder.this.interfaceClass), command);
            return this;
        }

        @SneakyThrows
        public WatcherBuilder<I> addListener(ListenerCommand<I> command, String name, Class<?>... params) {
            Method method = WatcherBuilder.this.interfaceClass.getDeclaredMethod(name, params);
            WatcherBuilder.this.interceptors.put(method, command);
            return this;
        }
    }


    //@Singular
    private final Map<Method, ListenerCommand<I>> interceptors;


    private Class<I> interfaceClass;
    private I original;

    @SuppressWarnings("unchecked")
    public I getProxy() {
        return (I) Proxy.newProxyInstance(
                LazyFactory.class.getClassLoader(),
                new Class[]{interfaceClass},
                new Watcher.DynamicInvocationHandler());
    }

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

    @FunctionalInterface
	public interface FunctionWithExceptions<T,R> extends Function<T,R>{

		R applyWithException(T t) throws Exception;

		@SneakyThrows
		default R apply(T t){
			return applyWithException(t);
		}
	}
}
