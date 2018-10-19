package com.ankan.educational.dao;

import java.util.List;

import com.ankan.educational.model.Question;

public interface QuestionDAO {

	public void saveOrUpdate(Question question);
	public void delete(int id);
	public Question get(int id);
	public List<Question>list(int tid);
}
