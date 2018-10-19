package com.ankan.educational.dao;

import java.util.List;

import com.ankan.educational.model.Question;
import com.ankan.educational.model.TestQuestions;

public interface TestQuestionsDAO {

	public void saveOrUpdate(TestQuestions tq);
	public void delete(int tid,int qid);
//	public Questions getQuestions(int tid);
	public List<Question>list(int tid,int uid);
}
