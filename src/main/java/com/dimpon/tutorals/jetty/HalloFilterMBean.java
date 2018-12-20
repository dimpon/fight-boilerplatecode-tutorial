package com.dimpon.tutorals.jetty;

public interface HalloFilterMBean {

	int get_maxRequestsPerSec();

	void set_maxRequestsPerSec(int _maxRequestsPerSec);

	long get_delayMs();

	void set_delayMs(long _delayMs);

	long get_throttleMs();

	void set_throttleMs(long _throttleMs);

	long get_maxWaitMs();

	void set_maxWaitMs(long _maxWaitMs);

	long get_maxRequestMs();

	void set_maxRequestMs(long _maxRequestMs);

	long get_maxIdleTrackerMs();

	void set_maxIdleTrackerMs(long _maxIdleTrackerMs);

	boolean is_insertHeaders();

	void set_insertHeaders(boolean _insertHeaders);

	boolean is_trackSessions();

	void set_trackSessions(boolean _trackSessions);

	boolean is_remotePort();

	void set_remotePort(boolean _remotePort);

	int get_throttledRequests();

	void set_throttledRequests(int _throttledRequests);
}
