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

/**
 * @author rajivranjan
 * @version 1.0
 * @organization Actoserba
 * @Date 06-Apr-2019
 */
public class InvalidInputException extends CustomException {

	/**
	 * @author rajivranjan
	 * @version 1.0
	 * @organization Actoserba
	 * @Date 06-Apr-2019 InvalidInputException.java
	 */
	private static final long serialVersionUID = 1L;
	private static final CustomMsgType customMsgTypes = CustomMsgType.INVALID_INPUT;

	public InvalidInputException(String key, Throwable e) {
		super(customMsgTypes, new Object[] { key }, e);
	}

	public InvalidInputException(String key) {
		super(customMsgTypes, new Object[] { key });
	}

	public InvalidInputException(Object... messageParams) {
		super(customMsgTypes, messageParams);
	}

}
