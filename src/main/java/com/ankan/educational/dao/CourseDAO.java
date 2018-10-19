package com.ankan.educational.dao;

import java.util.List;

import com.ankan.educational.model.Course;


public interface CourseDAO {

	public void saveOrUpdate(Course course);
	public void delete(int id);
	public Course get(int id);
	public List<Course>list(int uid);
	public List<Course>allCourses();
	public List<Integer>containSection();
}
