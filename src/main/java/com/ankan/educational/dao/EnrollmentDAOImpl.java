package com.ankan.educational.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ankan.educational.model.Enrollment;

public class EnrollmentDAOImpl implements EnrollmentDAO {

	

	@Autowired
	DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public EnrollmentDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public EnrollmentDAOImpl(DataSource dataSource) {
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Enrollment enrollment) {
		// TODO Auto-generated method stub
		String sql = "INSERT into enrollment(doe,invoice,courseid,userid) values(now(),?,?,?)";
		jdbcTemplate.update(sql, new Object[] { enrollment.getInvoice(),enrollment.getCourseid(),enrollment.getUserid() });
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Enrollment get(int id) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<Integer> listCourses(int uid) {
		// TODO Auto-generated method stub
		String sql = "SELECT courseid FROM enrollment,fees where enrollment.invoice=fees.invoice and uid = "+uid;
		List<Integer>ids = new ArrayList<>();
		List< Map<String, Object> > rows = jdbcTemplate.queryForList(sql);
		for(Map<String,Object> row : rows)
		{
			ids.add((Integer) row.get("courseid"));
		}
		return ids;
	}
	
	@Override
	public List<Integer>getallCoursesEnrollesbyAny() {
		String sql = "SELECT courseid from enrollment";
		List<Integer>ids = new ArrayList<>();
		List< Map<String, Object> > rows = jdbcTemplate.queryForList(sql);
		for(Map<String,Object> row : rows)
		{
			ids.add((Integer) row.get("courseid"));
		}
		return ids;
	}

	@Override
	public int getNoOfStudents(int courseid) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) as total from enrollment where courseid = "+courseid;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					int total = rs.getInt("total");
					return total;
				}
				return null;
			}

		});
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		String sql = "select max(id) as id from enrollment";

		return jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					int id = rs.getInt("id");
					return id;
				}
				return 0;
			}

		});
	}
	
	

}
