package com.ankan.educational.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.ankan.educational.model.Test;

public class TestDAOImpl implements TestDAO {

	@Autowired
	DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	public TestDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	

	public TestDAOImpl(DataSource dataSource) {
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Override
	public void saveOrUpdate(Test test) {
		// TODO Auto-generated method stub

		if (test.getId()==-1) {
			String sql = "INSERT into test(description,duration,maxScore) values(?,?,?)";
			jdbcTemplate.update(sql, new Object[] { test.getDescription(),test.getDuration(),test.getMaxScore() });
		} else {
			String sql = "UPDATE test SET description=?, duration=?, maxScore=? where id = ?";
			jdbcTemplate.update(sql, new Object[] { test.getDescription(),test.getDuration(),test.getMaxScore(),test.getId()});
		}
		return;
		
	}


	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM test WHERE id=?";
		jdbcTemplate.update(sql, id);
		return;
		
	}


	@Override
	public Test get(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM test WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Test>() {

			@Override
			public Test extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Test test = new Test();
					test.setId(rs.getInt("id"));
					test.setDescription(rs.getString("description"));
					test.setDuration(rs.getInt("duration"));
					test.setMaxScore(rs.getInt("maxScore"));
					return test;
				}
				return null;
			}

		});
	}

	@Override
	public List<Test> list() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM test";
		List<Test> tests = jdbcTemplate.query(sql, new RowMapper<Test>() {
			 
	        @Override
	        public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Test test = new Test();
				test.setId(rs.getInt("id"));
				test.setDescription(rs.getString("description"));
				test.setDuration(rs.getInt("duration"));
				test.setMaxScore(rs.getInt("maxScore"));
	            return test;
	        }
	 
	    });
		return tests;
	}


	@Override
	public int getID() {
		// TODO Auto-generated method stub
		String sql = "select max(id) as id from test";

		return jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					int id = rs.getInt("id");
					return id;
				}
				return null;
			}

		});
	}

}
