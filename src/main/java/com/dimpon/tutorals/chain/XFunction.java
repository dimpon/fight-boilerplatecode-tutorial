package com.dimpon.tutorals.chain;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class XFunction<T, R> implements Function<T, R> {

	private final List<Function> functions;

	private XFunction(XFunctionBuilder<R, T> builder) {
		this.functions = builder.functions;
	}

	@SuppressWarnings("unchecked")
	@Override
	public R apply(T t) {
		return (R) functions.stream().reduce(Function.identity(), Function::andThen).apply(t);
	}

	static class XFunctionBuilder<S, T> {

		public static <T> XFunctionBuilder<T, T> builder() {
			return new XFunctionBuilder<>();
		}

		List<Function> functions = new ArrayList<>();
		List<Supplier> suppliers = new ArrayList<>();

		@SuppressWarnings("unchecked")
		public <V> XFunctionBuilder<V, T> addFunction(Function<? super S, ? extends V> function) {
			functions.add(function);
			return (XFunctionBuilder<V, T>) this;
		}

		@SuppressWarnings("unchecked")
		public <V> XFunctionBuilder<V, T> addFunction(Supplier<Function<? super S, ? extends V>> supplier) {
			suppliers.add(supplier);
			return (XFunctionBuilder<V, T>) this;
		}

		public XFunction<T, S> create() {


			
			return new XFunction<>(this);
		}

	}

	public static void main(String[] args) {

		Function<A, B> ab = a -> new B(a);

		Function<A, E> adxFunction = XFunctionBuilder.<A> builder()
				.addFunction(ab)
				.addFunction(b -> new C(b))
				.addFunction(c -> new D(c))
				.addFunction(d -> new E(d))
				.create();

		Function<A, E> adxFunction1 = XFunctionBuilder.<A> builder()
				.addFunction(a -> new B(a))
				.addFunction(XFunctionBuilder.<B> builder()
						.addFunction(b -> new C(b))
						.addFunction(c -> new D(c))
						.create())
				.addFunction(d -> new E(d))
				.create();

		E transform = adxFunction1.apply(new A(123));

		log.info("rezz==" + transform.toString());

	}

	static class A {
		public A(Integer i) {
			log.info(i.toString());
		}
	}

	static class B {
		public B(A i) {
			log.info(i.toString());
		}
	}

	static class C {
		public C(B i) {
			log.info(i.toString());
		}
	}

	static class D {
		public D(C i) {
			log.info(i.toString());
		}
	}

	static class E {
		public E(D i) {
			log.info(i.toString());
		}
	}
}
