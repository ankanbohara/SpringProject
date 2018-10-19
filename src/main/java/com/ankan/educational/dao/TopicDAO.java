package com.ankan.educational.dao;

import java.util.List;

import com.ankan.educational.model.Topic;

public interface TopicDAO {

	public void saveorUpdate(Topic topic);
	public void delete(int id);
	public Topic get(int id);
	public List<Topic>list(int sectionid,int courseid);
	public List<Integer> containMaterial();
	public List<Topic>allTopics();
}
