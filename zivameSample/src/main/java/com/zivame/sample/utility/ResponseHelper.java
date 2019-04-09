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
package com.zivame.sample.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.zivame.sample.Exception.CustomException;
import com.zivame.sample.Exception.CustomMsgType;
import com.zivame.sample.Exception.InvalidInputException;
import com.zivame.sample.config.JsonMixin;

import lombok.extern.log4j.Log4j2;

/**
 * @author rajivranjan
 * @version 1.0
 * @organization Actoserba
 * @Date 08-Apr-2019
 */
@Log4j2
public class ResponseHelper {

	private static final SimpleFilterProvider DEFAULT_FILTER_PROVIDER = new SimpleFilterProvider();

	private static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(Timestamp.class, (JsonSerializer<Timestamp>) (src, typeOfSrc, context) -> new JsonPrimitive(src.getTime()))
			.create();

	public static Response getSuccessResponse(Status status, Object object) {
		return Response.status(status).type(MediaType.APPLICATION_JSON).entity(object).build();

	}

	public static Response getErrorResponse(CustomException e) {
		String result = buildResponse(e.toMessage(), null, e, null, null);
		return Response.status(getHttpErrorCode(e)).type(MediaType.APPLICATION_JSON).entity(result).build();
	}

	private static Status getHttpErrorCode(Exception e) {
		Status code = Response.Status.INTERNAL_SERVER_ERROR;
		if (e instanceof InvalidInputException) {
			code = Response.Status.NOT_FOUND;
			return code;
		}
		return code;
	}

	public static Response getErrorResponse(Exception e, CustomMsgType unknown) {
		Map<String, String> map = new HashMap<>();
		map.put("message", e.getMessage());
		map.put("status", "Faliure");
		return Response.status(getHttpErrorCode(e)).type(MediaType.APPLICATION_JSON).entity(map).build();
	}

	private static String buildResponse(String defaultMessage, Object data, Throwable e,
			SimpleFilterProvider filterProvider, JsonMixin[] mixins) {
		Map<String, Object> result = new HashMap<>();
		if ("success".equalsIgnoreCase(defaultMessage)) {
			result.put("status", defaultMessage);
		}
		if (data != null) {
			result.put("data", data);
		}
		if (e != null) {
			/*
			 * if (e instanceof AuthenticationCredentialsNotFoundException) { defaultMessage = CustomMsgType.ACCESS_DENIED.getMessage(); }
			 */
			if (log.isDebugEnabled()) {
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				result.put("stackTrace", errors.toString());
			}
		}
		result.put("message", defaultMessage);
		if (filterProvider == null) {
			filterProvider = DEFAULT_FILTER_PROVIDER;
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			if (mixins != null) {

				for (JsonMixin mixin : mixins) {
					mapper.addMixIn(mixin.getTargetClass(), mixin.getMixinClass());
				}

			}
			ObjectWriter writer = mapper.writer(filterProvider.setFailOnUnknownId(false));
			return writer.writeValueAsString(result);
		} catch (JsonProcessingException ex) {
			log.error("error serialising the response:" + data, ex);
			return GSON.toJson(result);
		}
	}

}
