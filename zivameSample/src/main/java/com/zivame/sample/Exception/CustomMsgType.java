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
package com.zivame.sample.Exception;

import java.util.ResourceBundle;

/**
 * @author rajivranjan
 * @version 1.0
 * @organization Actoserba
 * @Date 06-Apr-2019
 */
public enum CustomMsgType {
	/* Common error messages code */
	UNKNOWN(5000), INVALID_INPUT(5001, "The Input");

	private final Integer value;
	private Object[] defaultParams;

	CustomMsgType(Integer value, Object... defaultParams) {
		this.value = value;
		this.defaultParams = defaultParams;
	}

	public Integer getCode() {
		return value;
	}

	public Object[] getDefaultParams() {
		return defaultParams;
	}

	public String getMessage() {
		return String.format(getCustomMessage(String.valueOf(value)), defaultParams);
	}

	public String getMessage(Object[] params) {
		if (params == null) {
			return getMessage();
		}
		Object[] temp = new Object[defaultParams.length];
		for (int i = 0; i < defaultParams.length; i++) {
			temp[i] = params.length <= i || params[i] == null ? String.valueOf(defaultParams[i]) : String.valueOf(params[i]).trim();
		}
		return String.format(getCustomMessage(String.valueOf(value)), temp);
	}

	private static ResourceBundle customBundle = null;

	private static ResourceBundle getInstance() {
		if (null == customBundle) {
			customBundle = ResourceBundle.getBundle("message");
		}
		return customBundle;
	}

	private static String getCustomMessage(String msgCode) {
		return getInstance().getString(msgCode);
	}
}
