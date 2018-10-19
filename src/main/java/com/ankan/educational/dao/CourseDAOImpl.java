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
import org.springframework.jdbc.core.RowMapper;

import com.ankan.educational.model.Course;

public class CourseDAOImpl implements CourseDAO {

	@Autowired
	DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public CourseDAOImpl() {

	}

	public CourseDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Course course) {
		// TODO Auto-generated method stub

		if (get(course.getId()) == null) {
			String sql = "INSERT into course values(?,?,?,?,?,0)";
			jdbcTemplate.update(sql, new Object[] { course.getId(), course.getName(), course.getDescription(),
					course.isActive(), course.getPrice() });
		} else {
			String sql = "UPDATE course SET name=?, description=?, active=?, " + "price = ?,noOfEnrolled = ? WHERE id=?";
			jdbcTemplate.update(sql, new Object[] { course.getName(), course.getDescription(), course.isActive(),
					course.getPrice(), course.getNoOfEnrolled(),course.getId() });
		}
//		delete(course.getId());
		return;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM course WHERE id=?";
		jdbcTemplate.update(sql, id);
		return;
	}

	@Override
	public Course get(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM course WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Course>() {

			@Override
			public Course extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Course course = new Course();
					course.setId(rs.getInt("id"));
					course.setName(rs.getString("name"));
					course.setDescription(rs.getString("description"));
					course.setActive(rs.getBoolean("active"));
					course.setPrice(rs.getInt("price"));
					course.setNoOfEnrolled(rs.getInt("noOfEnrolled"));
					return course;
				}
				return null;
			}

		});
	}

	@Override
	public List<Course> list(int uid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM course where id not in(select courseid from enrollment where userid = "+uid+")";
		List<Course> courses = jdbcTemplate.query(sql, new RowMapper<Course>() {
			 
	        @Override
	        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Course course = new Course();
				course.setId(rs.getInt("id"));
				course.setName(rs.getString("name"));
				course.setDescription(rs.getString("description"));
				course.setActive(rs.getBoolean("active"));
				course.setPrice(rs.getInt("price"));
				course.setNoOfEnrolled(rs.getInt("noOfEnrolled"));
	            return course;
	        }
	 
	    });
		return courses;
	}
	
	@Override
	public List<Course> allCourses()
	{
		String sql = "SELECT * FROM course";
		List<Course> courses = jdbcTemplate.query(sql, new RowMapper<Course>() {
			 
	        @Override
	        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Course course = new Course();
				course.setId(rs.getInt("id"));
				course.setName(rs.getString("name"));
				course.setDescription(rs.getString("description"));
				course.setActive(rs.getBoolean("active"));
				course.setPrice(rs.getInt("price"));
				course.setNoOfEnrolled(rs.getInt("noOfEnrolled"));
	            return course;
	        }
	 
	    });
		return courses;
	}

	@Override
	public List<Integer> containSection() {
		// TODO Auto-generated method stub
		
			String sql = "SELECT courseid from section";
			List<Integer>ids = new ArrayList<>();
			List< Map<String, Object> > rows = jdbcTemplate.queryForList(sql);
			for(Map<String,Object> row : rows)
			{
				ids.add((Integer) row.get("courseid"));
			}
			return ids;
	}

}
