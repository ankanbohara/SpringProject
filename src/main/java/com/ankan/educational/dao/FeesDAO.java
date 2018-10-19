package com.ankan.educational.dao;

import java.util.List;

import com.ankan.educational.model.Fees;

public interface FeesDAO {
	public void saveOrUpdate(Fees fees);
	public void delete(String invoice);
	public Fees get(String invoice);
	public List<Fees>list();
}
