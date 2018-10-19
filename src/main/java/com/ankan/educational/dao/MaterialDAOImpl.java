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

import com.ankan.educational.model.Material;

public class MaterialDAOImpl implements MaterialDAO {

	@Autowired
	DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public MaterialDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public MaterialDAOImpl() {

		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveOrUpdate(Material material) {
		// TODO Auto-generated method stub
		String sql = "insert into material(name, topicid) values(?,?)";
		
		jdbcTemplate.update(sql, new Object[] { material.getName(), material.getTopicid() });
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub'
		String sql = "DELETE FROM material WHERE id = "+id;
		jdbcTemplate.update(sql);
		return;

	}

	@Override
	public Material get(int topicid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Material getTests() {

		String sql = "SELECT * FROM material WHERE name = 'Tests' ";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Material>() {

			@Override
			public Material extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Material material = new Material();
					material.setId(rs.getInt("id"));
					material.setName(rs.getString("name"));
					material.setTopicid(rs.getInt("topicid"));
					return material;
				}
				return null;
			}

		});
	}

	@Override
	public List<Material> list(int topicid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM material where topicid = " + topicid;
		List<Material> materials = jdbcTemplate.query(sql, new RowMapper<Material>() {

			@Override
			public Material mapRow(ResultSet rs, int rowNum) throws SQLException {
				Material material = new Material();
				material.setId(rs.getInt("id"));
				material.setName(rs.getString("name"));
				material.setTopicid(rs.getInt("topicid"));
				return material;
			}

		});
		return materials;
	}

	@Override
	public List<String> urls(int id) {
		String sql = "SELECT vlink FROM material,material_links where id = " + id + " and id = materialid";
		List<String> links = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) {
			links.add((String) row.get("vlink"));
		}
		return links;
	}

	@Override
	public List<String> allVideoLinks(int topicid) {
		// TODO Auto-generated method stub
		String sql = "SELECT materialid,vlink FROM material,material_links where name = 'Video Links' and topicid = " + topicid
				+ " and id = materialid";
		List<String> links = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> row : rows) {
			links.add((String) row.get("vlink"));
		}
		return links;
	}

	@Override
	public void insertInMaterialLinks(int id,String link) {
		// TODO Auto-generated method stub
		String sql = "INSERT into material_links values(?,?)";
		jdbcTemplate.update(sql,new Object[] {id,link});
		return;
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		String sql = "select max(id) as id from material";

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

	@Override
	public void deleteMaterial(String link) {
		// TODO Auto-generated method stub
		String sql = "DELETE from material_links where vlink = '"+link+"'";
		jdbcTemplate.update(sql);
		return;
	}

	@Override
	public Integer getMaterialFromLinks(String link) {
		// TODO Auto-generated method stub
		String sql = "select materialid from material_links where vlink = "+link;

		return jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					int id = rs.getInt("materialid");
					return id;
				}
				return null;
			}
		});
	}

	@Override
	public String getUrlOfMaterial(int id) {
		// TODO Auto-generated method stub
		String sql = "select vlink from material_links where materialid = "+id;

		return jdbcTemplate.query(sql, new ResultSetExtractor<String>() {

			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String link = rs.getString("vlink");
					return link;
				}
				return null;
			}
		});
	}

}
