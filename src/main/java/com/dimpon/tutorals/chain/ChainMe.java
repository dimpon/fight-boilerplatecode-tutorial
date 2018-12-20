package com.dimpon.tutorals.chain;

import lombok.NoArgsConstructor;
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

	// List<Function<?, ?>> functions;

	public ChainMe() {
	}

	ChainBuilder<R> bu;

	private ChainBuilder<S> newBu() {
		return new ChainBuilder<>();
	}

	void serBu(ChainBuilder<R> bu) {
		this.bu = bu;
	}

	class ChainBuilder<S> {

		List<Function<?, ?>> functions = new ArrayList<>();

		@SuppressWarnings("unchecked")
		private <V> ChainBuilder<V> addFunction(Function<? super S, ? extends V> function) {
			functions.add(function);
			return (ChainBuilder<V>) this;
		}

	}

	/*
	 * @SuppressWarnings("unchecked")
	 * private <I> ChainMe<Ss, R, I> addFunction(Function<S, R> function) {
	 * functions.add(function);
	 * return (ChainMe<Ss, R, I>) this;
	 * }
	 */

	public R transform(S source) {

		Object rez = source;

		for (int i = 0; i < bu.functions.size(); i++) {

			Function function = bu.functions.get(i);
			rez = function.apply(rez);

		}
		return (R) rez;

	}

/*	private Function<S, R> combineFunctions() {

		return bu.functions.stream().reduce(Function.identity(),(function, function2) -> function.andThen(function2));

		//return functions.stream().reduce(Function.identity(), Function::andThen);
	}*/



	/*
	 * <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
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

		/*
		 * E transform = new ChainMe<Integer, A>()
		 * .addFunction(a -> new B(a))
		 * .addFunction(b -> new C(b))
		 * .addFunction(c -> new D(c))
		 * .addFunction(d -> new E(d))
		 * .transform(new A(1));
		 */

		ChainMe<A, E> integerEChainMe = new ChainMe<>();

		integerEChainMe.serBu(
				integerEChainMe.newBu()
						.addFunction(a -> new B(a))
						.addFunction(b -> new C(b))
						.addFunction(c -> new D(c))
						.addFunction(d -> new E(d))
		);

		E transform = integerEChainMe.transform(new A(123));


		log.info("rezz==" + transform.toString());

		// .transform(Integer.valueOf(12));

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
