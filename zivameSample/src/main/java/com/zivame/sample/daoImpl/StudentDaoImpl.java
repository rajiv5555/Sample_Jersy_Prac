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
package com.zivame.sample.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;
import com.zivame.sample.dao.StudentDao;
import com.zivame.sample.model.Student;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * @author rajivranjan
 * @version 1.0
 * @organization Actoserba
 * @Date 07-Apr-2019
 */
@Repository
@Log4j2
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Student student) {
		log.info("inside dao layer : jdbctemplate" + jdbcTemplate);
		String sql = "insert into student(student_name) values(?)";
		String sql1 = "insert into student_details(home_address,school_address,school_name,student_student_id) values(?,?,?,?)";
		KeyHolder keyHolder = insertAndGetKey(sql, new Object[] { student.getStudentName() });
		int id = keyHolder.getKey().intValue();
		insert(sql1, new Object[] { student.getStudentDetails().getHomeAddress(), student.getStudentDetails().getSchoolAddress(),
				student.getStudentDetails().getSchoolName(), id });
	}

	private KeyHolder insertAndGetKey(String sql, Object[] args) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(con -> {
			final PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			new ArgumentPreparedStatementSetter(args).setValues(ps);
			log.debug(ps);
			return ps;
		}, keyHolder);
		return keyHolder;
	}

	private int insert(String sql, Object[] args) {
		return jdbcTemplate.update(con -> {
			final PreparedStatement ps = con.prepareStatement(sql);
			new ArgumentPreparedStatementSetter(args).setValues(ps);
			log.debug(ps);
			return ps;
		});
	}

	@Override
	public Student get(int id) {
		String query = "select * from student s inner join student_details sd on s.student_id=sd.student_student_id where s.student_id=?";
		// Not work with nested opojo object
		// Result student = jdbcTemplate.queryForObject(query, new Object[] { id }, new BeanPropertyRowMapper<>(Result.class));
		// System.out.println(student.getStudent_details());
		try {
			Student student = (Student) jdbcTemplate.queryForObject(query, new Object[] { id }, new StudentRowMapper());
			return student;
		} catch (EmptyResultDataAccessException e) {
			log.error("empty result set" + e);
		} catch (Exception e) {
			log.error("something went wrong" + e);
		}
		return null;
	}

	static class StudentRowMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student = new Student();
			com.zivame.sample.model.StudentDetails studentDetails = new com.zivame.sample.model.StudentDetails();
			student.setStudentName(rs.getString("student_name"));
			student.setStudentId(rs.getInt("student_id"));
			studentDetails.setHomeAddress(rs.getString("home_address"));
			studentDetails.setSchoolAddress(rs.getString("school_address"));
			studentDetails.setSchoolName(rs.getString("school_name"));
			student.setStudentDetails(studentDetails);
			return student;
		}

	}

	@Getter
	@Setter
	static class Result {
		private int studentId;
		private String studentName;
		// private StudentDetails student_details;
		private String school_name;
		private String school_address;
		private String home_address;
	}

	@Setter
	@Getter
	static class StudentDetails {
		private String school_name;
		private String school_address;
		private String home_address;
	}
}