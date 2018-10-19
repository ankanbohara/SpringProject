package com.ankan.educational.model;

public class TestQuestions {

	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int testid;
	private int questionid;
	private int userid;



	public TestQuestions(int id, int testid, int questionid, int userid) {

		this.id = id;
		this.testid = testid;
		this.questionid = questionid;
		this.userid = userid;
	}

	public TestQuestions() {
	
		// TODO Auto-generated constructor stub
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getTestid() {
		return testid;
	}

	public void setTestid(int testid) {
		this.testid = testid;
	}

	public int getQuestionid() {
		return questionid;
	}

	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}

}
