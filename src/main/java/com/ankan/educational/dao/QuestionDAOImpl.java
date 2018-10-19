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

import com.ankan.educational.model.Question;

public class QuestionDAOImpl implements QuestionDAO {

	
	@Autowired
	DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public QuestionDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	public QuestionDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}



	@Override
	public void saveOrUpdate(Question question) {
		// TODO Auto-generated method stub
		if (question.getId()==-1) {
			String sql = "INSERT into question(description,option1,option2,option3,option4,topicid,correctans) values(?,?,?,?,?,?,?)";
			jdbcTemplate.update(sql, new Object[] { question.getDescription(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4(),question.getTopicid(),question.getCorrectans() });
		} else {
			String sql = "UPDATE question SET description = ?,option1 = ?,option2 = ?,option3 = ?,option4 = ?,topicid = ?,correctans = ? where id = ?";
			jdbcTemplate.update(sql, new Object[] { question.getDescription(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4(),question.getTopicid(),question.getCorrectans(),question.getId() });
		}
		return;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM question WHERE id=?";
		jdbcTemplate.update(sql, id);
		return;

	}

	@Override
	public Question get(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM question WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Question>() {

			@Override
			public Question extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Question question = new Question();
					question.setId(rs.getInt("id"));
					question.setDescription(rs.getString("description"));
					question.setOption1(rs.getString("option1"));
					question.setOption2(rs.getString("option2"));
					question.setOption3(rs.getString("option3"));
					question.setOption4(rs.getString("option4"));
					question.setTopicid(rs.getInt("topicid"));
					question.setCorrectans(rs.getInt("correctans"));
					return question;
				}

				return null;
			}

		});
	}

	@Override
	public List<Question> list(int tid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM question where topicid = "+tid;
		List<Question> questions = jdbcTemplate.query(sql, new RowMapper<Question>() {
			 
	        @Override
	        public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Question question = new Question();
				question.setId(rs.getInt("id"));
				question.setDescription(rs.getString("description"));
				question.setOption1(rs.getString("option1"));
				question.setOption2(rs.getString("option2"));
				question.setOption3(rs.getString("option3"));
				question.setOption4(rs.getString("option4"));
				question.setTopicid(rs.getInt("topicid"));
				question.setCorrectans(rs.getInt("correctans"));
				return question;
	        }
	 
	    });
		return questions;
	}

}
