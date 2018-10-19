package com.ankan.educational.dao;

import java.util.List;

import com.ankan.educational.model.Score;

public interface ScoreDAO {
	
	public void saveOrUpdate(Score score);
//	public void delete(int id);
//	public Course get(int id);
	public List<Score>list(int tid,int userid);
}
