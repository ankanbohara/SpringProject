package com.ankan.educational.dao;

import java.util.List;

import com.ankan.educational.model.Section;

public interface SectionDAO {

	public void saveOrUpdate(Section section);
	public void delete(int sectionid,int courseid);
	public Section get(int sectionid,int courseid);
	public List<Section>list(int courseid);
//	public List<Section>containsTopic(int courseid);
	public List<Integer>containTopic(int sectionid,int courseid);
	
}
