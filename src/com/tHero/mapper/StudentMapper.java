package com.tHero.mapper;

import com.tHero.domain.Student;


public interface StudentMapper {
	public Student getById(int id);
	public int add(Student student);
	public int update(Student student);
	public int delete(Student student);
	public Student getStudnetAndTeacher(int id);
	public Student selectSupervisor(int id);
	public int addStudent(Student student);
}	
