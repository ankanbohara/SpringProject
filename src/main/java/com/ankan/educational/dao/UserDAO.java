package com.ankan.educational.dao;

import java.util.List;

import com.ankan.educational.model.User;

public interface UserDAO {

	public void saveOrUpdate(User user);
	public void delete(int id);
	public User get(String username,String password);
	public List<User> list();
	public String getRole(String username,String password);
	public User getByUsername(String username);
}
