package com.ankan.educational.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ankan.educational.model.User;

public class UserDAOImpl implements UserDAO {

	@Autowired
	DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserDAOImpl() {

		// TODO Auto-generated constructor stub
	}

	public UserDAOImpl(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(User user) {
		// TODO Auto-generated method stub
		if (get(user.getUsername(), user.getPassword()) == null) {
			
			String sql = "INSERT into USERS(user_name,password,contact,address,email,authority) values(?,?,?,?,?,'ROLE_USER')";
			jdbcTemplate.update(sql, new Object[] { user.getUsername(),user.getPassword(),user.getContact(),user.getAddress(),user.getEmail() });
		}
		else
		{
			System.out.println("HI_ANKAN");
			String sql = "UPDATE USERS set password = ?,contact = ?,address = ?,email=? where user_name = ?";
			jdbcTemplate.update(sql,new Object[] {user.getPassword(),user.getContact(),user.getAddress(),user.getEmail(),user.getUsername()});
		}

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User get(String username, String password) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM USERS WHERE binary user_name = ? and binary password = ?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
			}
		}, new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					User user = new User();
					user.setUserid(rs.getInt("user_id"));
					user.setUsername(rs.getString("user_name"));
					user.setPassword(rs.getString("password"));
					user.setEnabled(rs.getBoolean("enabled"));
					user.setContact(rs.getString("contact"));
					user.setEmail(rs.getString("email"));
					user.setAddress(rs.getString("address"));
					return user;
				}
				return null;
			}

		});
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRole(String username,String password) {
		// TODO Auto-generated method stub
		String sql = "SELECT authority from USERS where user_name = ? and password = ?";

		return jdbcTemplate.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, username);
				preparedStatement.setString(2,password);
			}
		}, new ResultSetExtractor<String>() {

			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String role = rs.getString("authority");
					return role;
				}
				return null;
			}

		});
	}

	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM USERS WHERE user_name = ?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, username);
			}
		}, new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					User user = new User();
					user.setUserid(rs.getInt("user_id"));
					user.setUsername(rs.getString("user_name"));
					user.setPassword(rs.getString("password"));
					user.setEnabled(rs.getBoolean("enabled"));
					user.setContact(rs.getString("contact"));
					user.setEmail(rs.getString("email"));
					user.setAddress(rs.getString("address"));
					return user;
				}
				return null;
			}

		});
	}

}
