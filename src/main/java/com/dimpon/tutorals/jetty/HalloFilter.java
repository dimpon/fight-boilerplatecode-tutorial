package com.dimpon.tutorals.jetty;

import org.eclipse.jetty.servlets.DoSFilter;

import javax.management.*;
import javax.servlet.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * @author Dmitrii Ponomarev
 */
public class HalloFilter extends DoSFilter implements HalloFilterMBean {

	public int get_maxRequestsPerSec() {
		return _maxRequestsPerSec;
	}

	public void set_maxRequestsPerSec(int _maxRequestsPerSec) {
		this._maxRequestsPerSec = _maxRequestsPerSec;
	}

	public long get_delayMs() {
		return _delayMs;
	}

	public void set_delayMs(long _delayMs) {
		this._delayMs = _delayMs;
	}

	public long get_throttleMs() {
		return _throttleMs;
	}

	public void set_throttleMs(long _throttleMs) {
		this._throttleMs = _throttleMs;
	}

	public long get_maxWaitMs() {
		return _maxWaitMs;
	}

	public void set_maxWaitMs(long _maxWaitMs) {
		this._maxWaitMs = _maxWaitMs;
	}

	public long get_maxRequestMs() {
		return _maxRequestMs;
	}

	public void set_maxRequestMs(long _maxRequestMs) {
		this._maxRequestMs = _maxRequestMs;
	}

	public long get_maxIdleTrackerMs() {
		return _maxIdleTrackerMs;
	}

	public void set_maxIdleTrackerMs(long _maxIdleTrackerMs) {
		this._maxIdleTrackerMs = _maxIdleTrackerMs;
	}

	public boolean is_insertHeaders() {
		return _insertHeaders;
	}

	public void set_insertHeaders(boolean _insertHeaders) {
		this._insertHeaders = _insertHeaders;
	}

	public boolean is_trackSessions() {
		return _trackSessions;
	}

	public void set_trackSessions(boolean _trackSessions) {
		this._trackSessions = _trackSessions;
	}

	public boolean is_remotePort() {
		return _remotePort;
	}

	public void set_remotePort(boolean _remotePort) {
		this._remotePort = _remotePort;
	}

	public int get_throttledRequests() {
		return _throttledRequests;
	}

	public void set_throttledRequests(int _throttledRequests) {
		this._throttledRequests = _throttledRequests;
	}

	@Override
	public void init(FilterConfig filterConfig) {
		super.init(filterConfig);

		try {
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			ObjectName name = new ObjectName("com.dimpon.tutorals.jetty:type=HalloFilter");
			mbs.registerMBean(this, name);
		} catch (MalformedObjectNameException | InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException, ServletException {
		super.doFilter(request, response, filterchain);
		System.out.println("filter.." + _maxRequestsPerSec);
	}

	@Override
	protected String extractUserId(ServletRequest request) {
		return request.getRemoteAddr();
	}
}
