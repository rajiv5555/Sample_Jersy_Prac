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
package com.zivame.sample.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zivame.sample.dao.StudentDao;
import com.zivame.sample.model.Student;
import com.zivame.sample.service.StudentService;

import lombok.extern.log4j.Log4j2;

/**
 * @author rajivranjan
 * @version 1.0
 * @organization Actoserba
 * @Date 07-Apr-2019
 */
@Service
@Log4j2
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public void save(Student student) {
		log.info("insde sevice class save method" + student);
		studentDao.save(student);

	}

	@Override
	public Student get(int id) {
		log.info("inside service class get method:" + id);
		return studentDao.get(id);
	}

}
