/*************************************************************************
 *
 * ZIVAME CONFIDENTIAL
 * ___________________
 *
 *  (C) 2019 Actoserba
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Actoserba and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary may be covered by India and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Actoserba.
 */
package com.zivame.sample.config;

import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.ThreadContext;

/**
 * @author rajivranjan
 * @version 1.0
 * @organization Actoserba
 * @Date 08-Apr-2019
 */
public class MainFilter implements Filter {
	private final String serverID = System.getProperty("LOGGER_SERVER_ID") != null ? System.getProperty("LOGGER_SERVER_ID") : "a";
	private final Calendar CALENDAR = Calendar.getInstance();
	private final AtomicInteger requestCount = new AtomicInteger();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("inside init");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		initializeThreadContext((HttpServletRequest) request);
		chain.doFilter(request, response);

	}

	private void initializeThreadContext(HttpServletRequest request) {
		String requestId = generateRequestId();
		ThreadContext.clearAll();
		ThreadContext.put("requestId", requestId);
		ThreadContext.put("ctxLogLevel", request.getHeader("ctxLogLevel"));
	}

	private String generateRequestId() {
		return String.valueOf(Objects.hash(serverID, CALENDAR.get(Calendar.YEAR) - 2000, CALENDAR.get(Calendar.DAY_OF_YEAR),
				CALENDAR.get(Calendar.HOUR_OF_DAY), CALENDAR.get(Calendar.MINUTE), requestCount.incrementAndGet()));
	}

	@Override
	public void destroy() {
		System.out.println("inside destroy");

	}

}
