package com.ankan.educational.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ankan.educational.model.Score;

public class ScoreDAOImpl implements ScoreDAO {

	@Autowired
	DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ScoreDAOImpl() {

		// TODO Auto-generated constructor stub
	}

	public ScoreDAOImpl(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Score score) {
		// TODO Auto-generated method stub
		
		String sql = "INSERT into score(testid,maxScore,score,correct,userid,time,notattempted) values(?,?,?,?,?,now(),?)";
		jdbcTemplate.update(sql, new Object[] { score.getTestid(),score.getMaxScore(),score.getScore(),score.getCorrect(),score.getUserid(),score.getNotattempted()});
		return;

	}

	@Override
	public List<Score> list(int tid, int userid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * from score where testid = "+tid+" and userid = "+userid;
		List<Score> scores = jdbcTemplate.query(sql, new RowMapper<Score>() {
			 
	        @Override
	        public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Score score = new Score();
				score.setId(rs.getInt("id"));
				score.setMaxScore(rs.getInt("maxScore"));
				score.setCorrect(rs.getInt("correct"));
				score.setNotattempted(rs.getInt("notattempted"));
				score.setScore(rs.getInt("score"));
				score.setTime(rs.getString("time"));
				score.setTestid(rs.getInt("testid"));
				score.setUserid(rs.getInt("userid"));
	            return score;
	        }
	 
	    });
		return scores;
		
	}

}
