package com.tHero.domain;

public class Student {
	private Integer id;
	private String name;
	private String gender;
	private String major;
	private String grade;
	//ÃÌº”ΩÃ ¶
	private Teacher supervisor;
	public Teacher getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Teacher supervisor) {
		this.supervisor = supervisor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
}
