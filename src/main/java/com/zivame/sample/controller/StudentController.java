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
package com.zivame.sample.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zivame.sample.Exception.CustomException;
import com.zivame.sample.Exception.CustomMsgType;
import com.zivame.sample.Exception.InvalidInputException;
import com.zivame.sample.model.Student;
import com.zivame.sample.service.StudentService;
import com.zivame.sample.utility.Helper;
import com.zivame.sample.utility.ResponseHelper;

import lombok.extern.log4j.Log4j2;

/**
 * @author rajivranjan
 * @version 1.0
 * @organization Actoserba
 * @Date 05-Apr-2019
 */
@Path("/v1")
@Controller
@Log4j2
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GET
	@Path("/sample")
	public void sample() {
		System.out.println("hello world..");
	}

	// Post Data
	@POST
	@Path("/student")
	public Response save(Student student) {
		log.info("inside post student method:" + student);
		try {
			validate(student);
			studentService.save(student);
			Map<String, Object> result = new HashMap<>();
			result.put("message", "success");
			result.put("data", "");
			return ResponseHelper.getSuccessResponse(Status.OK, result);
		} catch (CustomException e) {
			return ResponseHelper.getErrorResponse(e);
		} catch (Exception e) {
			return ResponseHelper.getErrorResponse(e, CustomMsgType.UNKNOWN);
		}
	}

	@GET
	@Path("/student/{id}")
	public Response get(@PathParam("id") int id) {
		Student student = studentService.get(id);
		return ResponseHelper.getSuccessResponse(Status.OK, student);

	}

	private void validate(Student student) {
		Set<String> illegalValue = new HashSet<>();
		if (null == student) {
			illegalValue.add("student");
		} else {
			if (null == student.getStudentName()) {
				illegalValue.add("studentName");
			}
			if (student.getStudentDetails() == null) {
				illegalValue.add("studentDetails");
			}
			if (student.getStudentDetails() != null && null == student.getStudentDetails().getHomeAddress()) {
				illegalValue.add("homeAddress");
			}
			if (student.getStudentDetails() != null && null == student.getStudentDetails().getSchoolAddress()) {
				illegalValue.add("schoolAddress");
			}
			if (student.getStudentDetails() != null && null == student.getStudentDetails().getSchoolName()) {
				illegalValue.add("schoolName");
			}
		}

		checkAndThrowException(illegalValue);
	}

	private void checkAndThrowException(Set<String> illegalValue) {
		if (illegalValue != null && !illegalValue.isEmpty()) {
			log.error("The following fields are missing/invalid: "
					+ Helper.collapseList(illegalValue, ","));
			throw new InvalidInputException("The following fields are missing/invalid: "
					+ Helper.collapseList(illegalValue, ","));
		}
	}

}
