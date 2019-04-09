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

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * @author rajivranjan
 * @version 1.0
 * @organization Actoserba
 * @Date 06-Apr-2019
 */
@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

	/**
	 *
	 */
	public JerseyConfig() {

		packages("com.zivame.sample.controller");

		// below method is use to register class to glassfish container
		// register(Endpoint.class);
		// register(ReverseEndpoint.class);
	}

}
