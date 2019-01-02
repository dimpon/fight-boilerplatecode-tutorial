package com.dimpon.tutorals.concurrent;

import com.google.common.util.concurrent.RateLimiter;
import lombok.SneakyThrows;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Dmitrii Ponomarev
 */
public class Throttling {

	// Map<Long, Deque<Long>> sessions = new ConcurrentHashMap<>();

	final static RateLimiter rateLimiter1 = RateLimiter.create(2);
	final static RateLimiter rateLimiter2 = RateLimiter.create(2);
	final static RateLimiter rateLimiter3 = RateLimiter.create(5);

	final static AllowCall<Long> protector = AllowCall.create();

	static class MyTask implements Callable<Long> {

		final long number;

		public MyTask(long number) {
			this.number = number;
		}

		public long getNumber() {
			return number;
		}

		@Override
		public Long call() throws Exception {

			switch ((int) number) {
			case 1:
				rateLimiter1.acquire();
				break;
			case 2:
				rateLimiter2.acquire();
				break;
			case 3:
				rateLimiter3.acquire();
				break;
			}

			// TimeUnit.MILLISECONDS.sleep(10);
			boolean isAllowed = protector.isAllowed(number);
			System.out.println(number + "=" + isAllowed);
			if (!isAllowed && (number == 1 || number == 2)) {
				System.out.println("1 or 2 is affected!");
				System.exit(-1);
			}

			return number;
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Run...");

        long l = System.currentTimeMillis();
        long l1 = System.nanoTime();


        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = new ObjectName("com.dimpon.tutorals.concurrent:type=AllowCall");
		mbs.registerMBean(protector, name);

		// long[] sessions = { 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3 };

		final Random random = new Random();

		/*
		 * for (int a = 0; a < 1000; a++) {
		 * int i = random.nextInt(sessions.length - 1);
		 * System.out.println("sessions[i]=" + sessions[i]);
		 * builder.accept(sessions[i]);
		 * tasks.accept(new MyTask(sessions[i]));
		 * 
		 * }
		 */

		// Map<Long, Long> counted = builder.build().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		List<MyTask> callables1 = IntStream.range(0, 1000)
				.mapToObj(value -> {

					long num = 3;

					if(value%7==0)
						num = 1;
					else if(value%5==0)
						num = 2;


					return new MyTask(num);
				})
				.collect(Collectors.toList());

		final ExecutorService executor1 = Executors.newFixedThreadPool(20);

		executor1.invokeAll(callables1);

		executor1.shutdown();

		/*
		 * list.add("Hello");
		 * list.add("Hello");
		 * list.add("World");
		 */

		/*
		 * Map<Long, Long> counted = list.stream()
		 * .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		 */

		// List<MyTask> collect = IntStream.range(1, 1000).mapToObj(MyTask::new).collect(Collectors.toList());

		/*
		 * long x = 153L;
		 * 
		 * AtomicLong loo = new AtomicLong(123L);
		 * 
		 * loo.accumulateAndGet(x, (oldVal, newVal) -> {
		 * System.out.println("l:" + oldVal + " r:" + newVal);
		 * return (newVal > oldVal) ? newVal : oldVal;
		 * });
		 * 
		 * long andAdd = loo.getAndSet(555L);
		 * 
		 * System.out.println("loo:"+loo);
		 */

		/*
		 * MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		 * ObjectName myName = new ObjectName("com.dimpon.tutorals.concurrent:type=Throttling");
		 * Throttling mbean = new Throttling();
		 * mbs.registerMBean(mbean, myName);
		 * 
		 * mbean.goIt();
		 */


	}

	private void goIt() {

		// EvictingQueue<Integer> linkedBlockingDeque = EvictingQueue.create(5);

		LinkedTransferQueue<Integer> q = new LinkedTransferQueue<>();

		IntStream.range(0, 50).forEach(value -> {

			q.add(value);

		});

		List<MyTask> collect = IntStream.range(1, 1000).mapToObj(MyTask::new).collect(Collectors.toList());

		submitTasks(collect);

	}

	@SneakyThrows
	private void sleep() {
		Thread.sleep(100);
	}

	// rate is "1 permits per second"
	// final Executor executor = Executors.newFixedThreadPool(1);

	void submitTasks(List<MyTask> tasks) {
		for (MyTask task : tasks) {
			System.out.println("going to execute..");
			// rateLimiter.acquire(); // may wait
			// executor.execute(task);
		}
	}

	public void setRate(double rate) {
		// rateLimiter.setRate(rate);
	}



}
