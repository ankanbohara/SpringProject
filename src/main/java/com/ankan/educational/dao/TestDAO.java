package com.ankan.educational.dao;

import java.util.List;

import com.ankan.educational.model.Test;

public interface TestDAO {
	
	public void saveOrUpdate(Test test);
	public void delete(int id);
	public Test get(int id);
	public List<Test>list();
	public int getID();
}
