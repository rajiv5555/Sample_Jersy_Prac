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

/**
 * @author rajivranjan
 * @version 1.0
 * @organization Actoserba
 * @Date 08-Apr-2019
 */
public class JsonMixin {
	private Class<?> targetClass;
	private Class<?> mixinClass;

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public Class<?> getMixinClass() {
		return mixinClass;
	}

	public JsonMixin(Class<?> targetClass, Class<?> mixinClass) {
		this.targetClass = targetClass;
		this.mixinClass = mixinClass;
	}
}
