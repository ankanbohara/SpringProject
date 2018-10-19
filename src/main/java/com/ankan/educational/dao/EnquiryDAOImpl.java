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

import com.ankan.educational.model.Enquiry;

public class EnquiryDAOImpl implements EnquiryDAO {

	
	@Autowired
	DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public EnquiryDAOImpl() {
		
	}

	public EnquiryDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Enquiry enquiry) {
		// TODO Auto-generated method stub
		if (get(enquiry.getId()) == null) {
			String sql = "INSERT into enquiry(name,email,address,contact,date,description) values(?,?,?,?,now(),?)";
			jdbcTemplate.update(sql, new Object[] { enquiry.getName(),enquiry.getEmail(),enquiry.getAddress(),enquiry.getContact(),enquiry.getDescription() });
		} 
		else {
			String sql = "UPDATE enquiry SET responded= ? WHERE id=?";
			jdbcTemplate.update(sql, new Object[] { enquiry.isResponded(),enquiry.getId() });
		}
		return;
	}
	

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM enquiry WHERE id=?";
		jdbcTemplate.update(sql, id);
		return;
	}

	@Override
	public Enquiry get(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM enquiry WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Enquiry>() {

			@Override
			public Enquiry extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Enquiry enquiry = new Enquiry();
					enquiry.setId(rs.getInt("id"));
					enquiry.setName(rs.getString("name"));
					enquiry.setEmail(rs.getString("email"));
					enquiry.setAddress(rs.getString("address"));
					enquiry.setContact(rs.getString("contact"));
					enquiry.setDescription(rs.getString("description"));
					enquiry.setResponded(rs.getBoolean("responded"));
					return enquiry;
				}

				return null;
			}

		});
	}

	@Override
	public List<Enquiry> list() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM enquiry";
		List<Enquiry> enquiries = jdbcTemplate.query(sql, new RowMapper<Enquiry>() {
			 
	        @Override
	        public Enquiry mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Enquiry enquiry = new Enquiry();
				enquiry.setId(rs.getInt("id"));
				enquiry.setName(rs.getString("name"));
				enquiry.setEmail(rs.getString("email"));
				enquiry.setAddress(rs.getString("address"));
				enquiry.setContact(rs.getString("contact"));
				enquiry.setDescription(rs.getString("description"));
				enquiry.setResponded(rs.getBoolean("responded"));
				return enquiry;
	        }
	 
	    });
		return enquiries;
	}

	@Override
	public void toggleEnquiry(int id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE enquiry SET responded = 1-responded WHERE id=?";
		jdbcTemplate.update(sql, new Object[] {id });
		return;
		
	}

}
