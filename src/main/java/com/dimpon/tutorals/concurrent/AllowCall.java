package com.dimpon.tutorals.concurrent;

import com.google.common.util.concurrent.AtomicLongMap;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Dmitrii Ponomarev
 */
public class AllowCall<K> implements AllowCallMBean {

	/**
	 * allowed interval for calls of one session, in nano
	 */
	private long allowedInterval;
	private final AtomicLongMap<K> attemptsTomeStamps = AtomicLongMap.create();

	// sheduler run eviction procedure every 1 min
	private static final long period = 1L;

	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	// keys older then eviction_interval are removed
	private static final long eviction_interval = TimeUnit.MINUTES.toNanos(1L);

	private AllowCall() {
		this.allowedInterval = TimeUnit.NANOSECONDS.toNanos(1_000);
		scheduler.scheduleAtFixedRate(evict, period, period, TimeUnit.MINUTES);
	}

	public static <T> AllowCall<T> create() {
		return new AllowCall<>();
	}

	/**
	 * Methods puts the last time of invokation into {@link AllowCall#attemptsTomeStamps}
	 * returns true if the next call is later then {@link AllowCall#allowedInterval}
	 * return false if the net call is too frequent.
	 *
	 * @param key
	 *            key id
	 * @return true of false
	 */
	public boolean isAllowed(K key) {
		final AtomicBoolean allow = new AtomicBoolean(true);

		attemptsTomeStamps.getAndUpdate(key,  (o) -> {
			long n = System.nanoTime();
			//System.out.println("key:" + key + "(" + n + "-" + o + ")=" + (n - o) + " a:" + allowedInterval);
			allow.set(n - o > allowedInterval);
			return n;
		});
		return allow.get();
	}

	@Override
	public void setAllowedInterval(long interval) {
		this.allowedInterval = interval;
	}

	@Override
	public long getAllowedInterval() {
		return allowedInterval;
	}

	// Eviction procedure
	// Set 0 for all expired values, then remove them with one atomic operation.
	private final Runnable evict = () -> {
		attemptsTomeStamps.asMap().keySet().iterator().forEachRemaining(k -> {
			attemptsTomeStamps.getAndAccumulate(k, 0, (o, n) -> (System.nanoTime() - o > eviction_interval) ? n : o);
		});
		attemptsTomeStamps.removeAllZeros();
	};
}
