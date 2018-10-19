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

import com.ankan.educational.model.Section;

public class SectionDAOImpl implements SectionDAO {

	

	@Autowired
	DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public SectionDAOImpl() {
		
		// TODO Auto-generated constructor stub
	}
	
	

	public SectionDAOImpl(DataSource dataSource) {
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}



	@Override
	public void saveOrUpdate(Section section) {
		// TODO Auto-generated method stub

		if (get(section.getSectionid(),section.getCourseid()) == null) {
			String sql = "INSERT into section values(?,?,?,?)";
			jdbcTemplate.update(sql, new Object[] { section.getSectionid(),section.getCourseid(),section.getName(),section.getDescription() });
		} else {
			String sql = "UPDATE section SET name=?, description=? where sectionid = "+section.getSectionid()+" and courseid = "+section.getCourseid();
			jdbcTemplate.update(sql, new Object[] { section.getName(),section.getDescription() });
		}
		return;
	

	}

	@Override
	public void delete(int sectionid, int courseid) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM section WHERE sectionid=? and courseid = ?";
		jdbcTemplate.update(sql, sectionid,courseid);
		return;
	}

	@Override
	public Section get(int sectionid, int courseid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM section WHERE sectionid = " + sectionid +" and courseid = "+courseid;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Section>() {

			@Override
			public Section extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Section section = new Section();
					section.setSectionid(rs.getInt("sectionid"));
					section.setCourseid(rs.getInt("courseid"));
					section.setName(rs.getString("name"));
					section.setDescription(rs.getString("description"));	
					return section;
				}
				return null;
			}

		});
	}

	@Override
	public List<Section> list(int courseid) {
		String sql = "SELECT * FROM section where courseid = "+courseid;
		List<Section> sections = jdbcTemplate.query(sql, new RowMapper<Section>() {
			 
	        @Override
	        public Section mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Section section = new Section();
				section.setSectionid(rs.getInt("sectionid"));
				section.setCourseid(rs.getInt("courseid"));
				section.setName(rs.getString("name"));
				section.setDescription(rs.getString("description"));
				return section;
	        }
	 
	    });
		return sections;
	}



	@Override
	public List<Integer> containTopic(int sectionid, int courseid) {
		
		String sql = "SELECT sectionid from topic where sectionid=? and courseid=?";
		List<Integer>ids = new ArrayList<Integer>();
		List< Map<String, Object> > rows = jdbcTemplate.queryForList(sql,sectionid,courseid);
		for(Map<String,Object> row : rows)
		{
			ids.add((Integer) row.get("sectionid"));
		}
		return ids;
	}

}
