package com.ankan.educational.dao;

import java.util.List;

import com.ankan.educational.model.Material;

public interface MaterialDAO {

	public void saveOrUpdate(Material material);
	public void delete(int id);
	public Material get(int topicid);
	public List<Material>list(int topicid);
	public List<String> urls(int id);
	public Material getTests();
	public List<String>allVideoLinks(int topicid);
	public void insertInMaterialLinks(int id,String link);
	public int getID();
	public void deleteMaterial(String link);
	public Integer getMaterialFromLinks(String link);
	public String getUrlOfMaterial(int id);
}
