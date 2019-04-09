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
public class CustomException extends RuntimeException {

	/**
	 * @author rajivranjan
	 * @version 1.0
	 * @organization Actoserba
	 * @Date 06-Apr-2019 CustomException.java
	 */
	private static final long serialVersionUID = 1L;
	private final Integer errorCode;
	private final String message;

	public CustomException(CustomMsgType customMsgType, Throwable e, String loggingMessage) {
		super(e.getMessage() + ":" + loggingMessage, e);
		errorCode = customMsgType.getCode();
		message = customMsgType.getMessage();
	}

	public CustomException(CustomMsgType customMsgType, Object[] messageParams, Throwable e, String loggingMessage) {
		super(e.getMessage() + ":" + loggingMessage, e);
		errorCode = customMsgType.getCode();
		message = customMsgType.getMessage(messageParams);
	}

	public CustomException(CustomMsgType customMsgType, Throwable e) {
		super(e.getMessage(), e);
		errorCode = customMsgType.getCode();
		message = customMsgType.getMessage();
	}

	public CustomException(CustomMsgType customMsgType, Object[] messageParams, Throwable e) {
		super(e.getMessage(), e);
		errorCode = customMsgType.getCode();
		message = customMsgType.getMessage(messageParams);
	}

	public CustomException(CustomMsgType customMsgType, String loggingMessage) {
		super(loggingMessage);
		errorCode = customMsgType.getCode();
		message = customMsgType.getMessage();
	}

	public CustomException(CustomMsgType customMsgType, Object[] messageParams, String loggingMessage) {
		super(loggingMessage);
		errorCode = customMsgType.getCode();
		message = customMsgType.getMessage(messageParams);
	}

	public CustomException(CustomMsgType customMsgType) {
		super(customMsgType.getMessage());
		errorCode = customMsgType.getCode();
		message = customMsgType.getMessage();
	}

	public CustomException(CustomMsgType customMsgType, Object[] messageParams) {
		super(customMsgType.getMessage(messageParams));
		errorCode = customMsgType.getCode();
		message = customMsgType.getMessage(messageParams);
	}

	protected CustomException(Integer errorCode, String message, Throwable e, String loggingMessage) {
		super(e.getMessage() + ":" + loggingMessage, e);
		this.errorCode = errorCode;
		this.message = message;
	}

	protected CustomException(Integer errorCode, String message, Throwable e) {
		super(e.getMessage(), e);
		this.errorCode = errorCode;
		this.message = message;
	}

	protected CustomException(Integer errorCode, String message, String loggingMessage) {
		super(message + ":" + loggingMessage);
		this.errorCode = errorCode;
		this.message = message;
	}

	protected CustomException(Integer errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.message = message;
	}

	/**
	 * @return the error_code
	 */
	public Integer getCode() {
		return errorCode;
	}

	public String toMessage() {
		return message;
	}

}
