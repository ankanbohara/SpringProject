package com.ankan.educational.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ankan.educational.model.Question;
import com.ankan.educational.model.TestQuestions;

public class TestQuestionsDAOImpl implements TestQuestionsDAO {

	@Autowired
	DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public TestQuestionsDAOImpl() {

		// TODO Auto-generated constructor stub
	}

	public TestQuestionsDAOImpl(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(TestQuestions tq) {
		// TODO Auto-generated method stub

		String sql = "INSERT into testQuestions(testid,questionid,userid) values(?,?,?)";
		jdbcTemplate.update(sql, new Object[] { tq.getTestid(), tq.getQuestionid(),tq.getUserid() });
		return;

	}

	@Override
	public void delete(int tid, int qid) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public Questions getQuestions(int tid,int uid) {
//		// TODO Auto-generated method stub
//		String sql = "SELECT * FROM testQuestions WHERE testid = " + tid;
//		return jdbcTemplate.query(sql, new ResultSetExtractor<Questions>() {
//
//			@Override
//			public Course extractData(ResultSet rs) throws SQLException, DataAccessException {
//				if (rs.next()) {
//					Course course = new Course();
//					course.setId(rs.getInt("id"));
//					course.setName(rs.getString("name"));
//					course.setDescription(rs.getString("description"));
//					course.setActive(rs.getBoolean("active"));
//					course.setPrice(rs.getInt("price"));
//					return course;
//				}
//				return null;
//			}
//
//		});
//	}

	@Override
	public List<Question> list(int tid,int uid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * from question where id=ANY(select questionid from testQuestions where testid="+tid+" and userid="+uid;
		List<Question> questions = jdbcTemplate.query(sql, new RowMapper<Question>() {
			 
	        @Override
	        public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Question q = new Question();
				q.setId(rs.getInt("id"));
				q.setDescription(rs.getString("name"));
				q.setOption1(rs.getString("option1"));
				q.setOption2(rs.getString("option2"));
				q.setOption3(rs.getString("option3"));
				q.setOption4(rs.getString("option4"));
				q.setTopicid(rs.getInt("topicid"));
				q.setCorrectans(rs.getInt("correctans"));	
	            return q;
	        }
	 
	    });
		return questions;
	}

}
