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

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author rajivranjan
 * @version 1.0
 * @organization Actoserba
 * @Date 08-Apr-2019
 */
@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean getFilterObject() {
		FilterRegistrationBean registeredBean = new FilterRegistrationBean();
		MainFilter filter = new MainFilter();
		registeredBean.setFilter(filter);
		registeredBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registeredBean;
	}
}
