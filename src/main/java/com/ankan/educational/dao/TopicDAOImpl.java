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

import com.ankan.educational.model.Topic;

public class TopicDAOImpl implements TopicDAO {

	
	@Autowired
	DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	public TopicDAOImpl() {
		
		// TODO Auto-generated constructor stub
	}

	public TopicDAOImpl(DataSource dataSource) {
	
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveorUpdate(Topic topic) {
		// TODO Auto-generated method stub
		
		if (topic.getId()==-1) {
			String sql = "INSERT into topic(name,description,sectionid,courseid) values(?,?,?,?)";
			jdbcTemplate.update(sql, new Object[] { topic.getName(),topic.getDescription(),topic.getSectionid(),topic.getCourseid() });
		} else {
			String sql = "UPDATE topic SET name=?, description= ? where id = ?";
			jdbcTemplate.update(sql, new Object[] { topic.getName(),topic.getDescription(),topic.getId() });
		}
		return;
		
	}
	
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE from topic where id = ?";
		jdbcTemplate.update(sql,id);
	}

	@Override
	public Topic get(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM topic WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Topic>() {

			@Override
			public Topic extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Topic topic = new Topic();
		        	topic.setId(rs.getInt("id"));
		        	topic.setName(rs.getString("name"));
		        	topic.setDescription(rs.getString("description"));
		        	topic.setCourseid(rs.getInt("courseid"));
		        	topic.setSectionid(rs.getInt("sectionid"));
		            return topic;
				}
				return null;
			}

		});
	}

	@Override
	public List<Topic> list(int sectionid, int courseid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM topic where sectionid = "+sectionid+" and courseid = "+courseid;
		List<Topic> topics = jdbcTemplate.query(sql, new RowMapper<Topic>() {
			 
	        @Override
	        public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Topic topic = new Topic();
	        	topic.setId(rs.getInt("id"));
	        	topic.setName(rs.getString("name"));
	        	topic.setDescription(rs.getString("description"));
	        	topic.setCourseid(rs.getInt("courseid"));
	        	topic.setSectionid(rs.getInt("sectionid"));
	            return topic;
	        }
	 
	    });
		return topics;
	}

	@Override
	public List<Integer> containMaterial() {
		// TODO Auto-generated method stub
		String sql = "select topicid from material";
		List<Integer>ids = new ArrayList<Integer>();
		List< Map<String, Object> > rows = jdbcTemplate.queryForList(sql);
		for(Map<String,Object> row : rows)
		{
			ids.add((Integer) row.get("topicid"));
		}
		return ids;
	}

	@Override
	public List<Topic> allTopics() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM topic";
		List<Topic> topics = jdbcTemplate.query(sql, new RowMapper<Topic>() {
			 
	        @Override
	        public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Topic topic = new Topic();
	        	topic.setId(rs.getInt("id"));
	        	topic.setName(rs.getString("name"));
	        	topic.setDescription(rs.getString("description"));
	        	topic.setCourseid(rs.getInt("courseid"));
	        	topic.setSectionid(rs.getInt("sectionid"));
	            return topic;
	        }
	 
	    });
		return topics;
	}

	

}
