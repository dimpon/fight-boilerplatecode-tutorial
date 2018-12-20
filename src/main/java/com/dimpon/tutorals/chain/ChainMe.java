package com.dimpon.tutorals.chain;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Dmitrii Ponomarev
 */
@Slf4j
public class ChainMe<S, R> {

	private List<Function<?, ?>> functions = new ArrayList<>();

	/*
	 * @SuppressWarnings("unchecked")
	 * private <I> ChainMe<Ss, R, I> addFunction(Function<S, R> function) {
	 * functions.add(function);
	 * return (ChainMe<Ss, R, I>) this;
	 * }
	 */

	@SuppressWarnings("unchecked")
	private <V> ChainMe<S, V> addFunction(Function<R,  V> function) {
		functions.add(function);
		return (ChainMe<S, V>) this;
	}

	public R transform(Object source) {

		Object rez = source;

		for (int i = 0; i < functions.size(); i++) {

			Function function = functions.get(i);
			rez = function.apply(rez);

		}
		return (R) rez;

	}

	/*
	 * default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
	 * Objects.requireNonNull(after);
	 * return (T t) -> after.apply(apply(t));
	 * }
	 */

	public static void main(String[] args) {

		/*
		 * ChainMe<E, String> eStringChainMe = new ChainMe<Integer, A>()
		 * .<B>addFunction(integer -> new A(integer))
		 * .<C>addFunction(a -> new B(a))
		 * .<D>addFunction(b -> new C(b))
		 * .<E>addFunction(c -> new D(c))
		 * .<String>addFunction(d -> new E(d));
		 */

		/*
		 * E transform = new ChainMe<Integer, Integer, A>()
		 * .<B>addFunction(A::new)
		 * .<C>addFunction(B::new)
		 * .<D>addFunction(C::new)
		 * .<E>addFunction(D::new)
		 * .<E>addFunction(E::new)
		 * .transform(123);
		 */

		E transform = new ChainMe<Integer, A>()
				.addFunction(a -> new B(a))
				.addFunction(b -> new C(b))
				.addFunction(c -> new D(c))
				.addFunction(d -> new E(d))
				.transform(new A(1));

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
