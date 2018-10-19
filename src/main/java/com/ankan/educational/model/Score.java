package com.ankan.educational.model;

public class Score {
	private int id;
	private int testid;
	private int maxScore;
	private int score;
	private int correct;
	private int userid;
	private String time;
	private int notattempted;
	public Score(int id, int testid, int maxScore, int score, int correct, int userid, String time, int notattempted) {
		this.id = id;
		this.testid = testid;
		this.maxScore = maxScore;
		this.score = score;
		this.correct = correct;
		this.userid = userid;
		this.time = time;
		this.notattempted = notattempted;
	}
	public Score() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTestid() {
		return testid;
	}
	public void setTestid(int testid) {
		this.testid = testid;
	}
	public int getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getCorrect() {
		return correct;
	}
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getNotattempted() {
		return notattempted;
	}
	public void setNotattempted(int notattempted) {
		this.notattempted = notattempted;
	}
	
	
}
