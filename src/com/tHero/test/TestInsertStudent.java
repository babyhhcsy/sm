package com.tHero.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.tHero.domain.Student;
import com.tHero.domain.Teacher;
import com.tHero.mapper.StudentMapper;


public class TestInsertStudent implements Controller{
	private static ApplicationContext ctx;

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentMapper mapper = (StudentMapper) ctx.getBean("studentMapper");
		Student student = new Student();
		student.setName("李林");
		student.setGender("男");
		student.setMajor("计算机科学技术");
		student.setGrade("2011");
		Teacher supervisor = new Teacher();
		supervisor.setId(1);
		student.setSupervisor(supervisor);
		mapper.addStudent(student);
		student.getSupervisor().getName();
		return new ModelAndView("/show","student",student);
	}

}
