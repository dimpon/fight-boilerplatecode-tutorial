package com.dimpon.tutorals.retry;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.concurrent.ThreadSafe;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * This class provides a proxy for object of arbitrary type. The original object is provided by {@link RepeatableCallsProxy#supplierOriginal}
 * The object must implement interface which is passed in order to create {@link java.lang.reflect.Proxy}
 * When a method of original object throws exception from predefined list {@link RepeatableCallsProxy#exceptions}
 * proxy do the next call after timeout {@link RepeatableCallsProxy#timeout}. It makes {@link RepeatableCallsProxy#attempts} attempts
 * every time calling {@link Supplier#get()} and re-creating (if Supplier really creates) the original object.
 * Default timeout is 100 milliseconds.
 * Default attapts is 3.
 *
 *
 * The class also contains the static factory methods for creating most usable Repeatable Calls Proxies for avoid verbosity.
 * e.g.{@link RepeatableCallsProxy#remoteServiceProxy(Class, Supplier)}
 *
 * The implementation uses the Builder patters. The sample of usage:
 *
 * <blockquote>
 *
 * <pre>
 *
 * TradeExportDataModelService tradeExportDataModelService = RepeatableCallsProxy.<TradeExportDataModelService> builder()
 * 		.interfaceClass(TradeExportDataModelService.class)
 * 		.supplier(() -> serviceRegistry.getUniqueSynchronousServiceFacade(TradeExportDataModelService.class))
 * 		.attempts(5)
 * 		.timeout(5000)
 * 		.exception(CommunicationException.class)
 * 		.exception(NoServiceFoundException.class)
 * 		.exception(NotUniqueServiceException.class)
 * 		.build()
 * 		.proxy();
 *
 * </pre>
 *
 * </blockquote>
 *
 * Sometimes the original object is already passed to a method (or constructor), them the code can be:
 *
 * <blockquote>
 *
 * <pre>
 *
 * TradeExportDataModelService tradeExportDataModelService = RepeatableCallsProxy.<TradeExportDataModelService> builder()
 * 		.interfaceClass(TradeExportDataModelService.class)
 * 		.supplier(() -> originalObject)
 * 		.exception(CommunicationException.class)
 * 		.build()
 * 		.proxy();// uses default timeout and attempts
 *
 * or
 *
 * TradeExportDataModelService service = 
 * RepeatableCallsProxy.remoteServiceProxy(TradeExportDataModelService.class,() -> serviceRegistry.getUniqueSynchronousServiceFacade(TradeExportDataModelService.class);
 *
 * </pre>
 *
 * </blockquote>
 *
 *
 * @author Dmitrii Ponomarev
 *
 */
@ThreadSafe
@Slf4j
public class RepeatableCallsProxy<I> {

	private Class<I> interfaceClass;
	private Supplier<I> supplierOriginal;

	private I original;

	private int attempts;
	private long timeout;
	private List<Class<? extends RuntimeException>> exceptions;

	private RepeatableCallsProxy(Class<I> interfaceClass, Supplier<I> supplierOriginal, int attempts, long timeout,
			List<Class<? extends RuntimeException>> exceptions) {

		if (interfaceClass == null)
			throw new IllegalArgumentException("InterfaceClass is required");

		if (supplierOriginal == null)
			throw new IllegalArgumentException("Supplier is required");

		this.interfaceClass = interfaceClass;
		this.supplierOriginal = supplierOriginal;
		this.attempts = attempts;
		this.timeout = timeout;
		this.exceptions = exceptions;
		this.original = this.supplierOriginal.get();
	}

	@SuppressWarnings("unchecked")
	public I proxy() {
		return (I) Proxy.newProxyInstance(
				RepeatableCallsProxy.class.getClassLoader(),
				new Class[] { interfaceClass },
				new RepeatableCallsProxy.DynamicInvocationHandler());
	}

	private class DynamicInvocationHandler implements InvocationHandler {

		@SuppressWarnings("unchecked")
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			int failedAttemptsLeft = attempts;
			return invokeIt(method, failedAttemptsLeft, args);
		}

		private Object invokeIt(Method method, int failedAttemptsLeft, Object... args) throws Throwable {

			log.info("attempts left {} original {}",failedAttemptsLeft,original.getClass().getName());

			try {
				return method.invoke(original, args);
			} catch (InvocationTargetException e) {
				final Throwable cause = e.getTargetException();
				failedAttemptsLeft--;

				if (exceptions.stream().anyMatch(aClass -> aClass.isInstance(cause))
						&& failedAttemptsLeft > 0) {

					log.warn("recoverable exception " + cause.getMessage());
					Thread.sleep(timeout);
					original = supplierOriginal.get();
					return invokeIt(method, failedAttemptsLeft, args);
				}

				log.error("final exception " + cause.getMessage());
				throw cause;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> RepeatableCallsProxy.RepeatableCallsProxyBuilder<T> builder() {
		return new RepeatableCallsProxy.RepeatableCallsProxyBuilder();
	}

	public static class RepeatableCallsProxyBuilder<I> {
		private Class<I> interfaceClass;
		private Supplier<I> supplierOriginal = null;

		private int attempts = 3;
		private long timeout = TimeUnit.MILLISECONDS.toMillis(100);
		private List<Class<? extends RuntimeException>> exceptions;

		/**
		 * Set the interface class. it is mandatory for creating {@link java.lang.reflect.Proxy}
		 *
		 * @param interfaceClass
		 *            interface of service
		 * @return builder object
		 */
		public RepeatableCallsProxyBuilder<I> interfaceClass(Class<I> interfaceClass) {
			this.interfaceClass = interfaceClass;
			return this;
		}

		/**
		 * Set the number of attempts
		 *
		 * @param attempts
		 *            number of attempts
		 * @return builder object
		 */
		public RepeatableCallsProxyBuilder<I> attempts(int attempts) {
			this.attempts = attempts;
			return this;
		}

		/**
		 * Set the timeout between attempts
		 *
		 * @param timeout
		 *            in milliseconds
		 * @return builder object
		 */
		public RepeatableCallsProxyBuilder<I> timeout(long timeout) {
			this.timeout = timeout;
			return this;
		}

		/**
		 * Set supplier which provides the original object
		 *
		 * @param supplierOriginal
		 *            supplier function
		 * @return builder object
		 */
		public RepeatableCallsProxyBuilder<I> supplier(Supplier<I> supplierOriginal) {
			this.supplierOriginal = supplierOriginal;
			return this;
		}

		/**
		 * Set one exception after ones the next call bust be done
		 *
		 * @param e
		 *            exception class
		 * @return builder object
		 */
		public RepeatableCallsProxyBuilder<I> exception(Class<? extends RuntimeException> e) {
			if (this.exceptions == null) {
				this.exceptions = new ArrayList<>();
			}
			this.exceptions.add(e);
			return this;
		}

		private RepeatableCallsProxyBuilder<I> exceptions(List<Class<? extends RuntimeException>> exceptions) {
			this.exceptions = exceptions;
			return this;
		}

		/**
		 * Build {@code RepeatableCallsProxy} object
		 *
		 * @return {@code RepeatableCallsProxy} object
		 */
		public RepeatableCallsProxy<I> build() {
			return new RepeatableCallsProxy<I>(interfaceClass, supplierOriginal, attempts, timeout, exceptions);
		}
	}

	/**
	 * Here the new factory methods for common used patterns of proxies can be placed.
	 */

	/*
	 * public static <T> T remoteServiceProxy(Class<T> interfaceClass, Supplier<T> supplier) {
	 * return remoteServiceProxy(interfaceClass, supplier, 5000);
	 * }
	 * 
	 * public static <T> T remoteServiceProxy(Class<T> interfaceClass, Supplier<T> supplier, int timeout) {
	 * 
	 * return RepeatableCallsProxy.<T> builder()
	 * .interfaceClass(interfaceClass)
	 * .supplier(supplier)
	 * .attempts(5)
	 * .timeout(timeout)
	 * .exceptions(SERVICE_FACADE_EXCEPTIONS)
	 * .build()
	 * .proxy();
	 * }
	 * 
	 *//*
		 * The sets of exceptions
		 *//*
			 * private static final List<Class<? extends RuntimeException>> SERVICE_FACADE_EXCEPTIONS = Arrays.asList(
			 * CommunicationException.class,
			 * NoServiceFoundException.class,
			 * NotUniqueServiceException.class);
			 */

}
