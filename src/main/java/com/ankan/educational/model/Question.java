package com.ankan.educational.model;

public class Question {

	private int id;
	private String description;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private int topicid;
	private int correctans;

	
	public Question() {
		
	}
	
	public Question(int id, String description, String option1, String option2, String option3, String option4,
			int topicid, int correctans) {
		this.id = id;
		this.description = description;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.topicid = topicid;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public int getTopicid() {
		return topicid;
	}
	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}
	public int getCorrectans() {
		return correctans;
	}
	public void setCorrectans(int correctans) {
		this.correctans = correctans;
	}
	
}
