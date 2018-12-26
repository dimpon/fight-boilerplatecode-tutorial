package com.dimpon.tutorals.concurrent;

/**
 * @author Dmitrii Ponomarev
 */
public interface AllowCallMBean {

	void setAllowedInterval(long interval);

	long getAllowedInterval();
}
