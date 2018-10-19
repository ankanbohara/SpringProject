package com.ankan.educational.dao;

import java.util.List;

import com.ankan.educational.model.Enrollment;

public interface EnrollmentDAO {

	public void saveOrUpdate(Enrollment enrollment);
	public void delete(int id);
	public Enrollment get(int id);
	public List<Integer>listCourses(int uid);
	public List<Integer>getallCoursesEnrollesbyAny();
	public int getNoOfStudents(int courseid);
	public int getID();
}
