package com.ankan.educational.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ankan.educational.model.Fees;

public class FeesDAOImpl implements FeesDAO {

	@Autowired
	DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public FeesDAOImpl() {

	}

	public FeesDAOImpl(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Fees fees) {
		// TODO Auto-generated method stub

		String sql = "INSERT into fees values(?,?,now(),?,?)";
		jdbcTemplate.update(sql, new Object[] { fees.getInvoice(), fees.getTid(), fees.getUid(), fees.getAmount() });
		return;
	}

	@Override
	public void delete(String invoice) {
		// TODO Auto-generated method stub

	}

	@Override
	public Fees get(String invoice) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM fees WHERE invoice = " + invoice;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Fees>() {

			@Override
			public Fees extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Fees fees = new Fees();
					fees.setInvoice(rs.getString("invoice"));
					fees.setTid(rs.getLong("tid"));
					fees.setAmount(rs.getDouble("amount"));
					fees.setUid(rs.getInt("uid"));
					return fees;
				}
				return null;
			}

		});
	}

	@Override
	public List<Fees> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
