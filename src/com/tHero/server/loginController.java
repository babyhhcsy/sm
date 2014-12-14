package com.tHero.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.tHero.domain.Student;
import com.tHero.mapper.StudentMapper;


public class loginController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentMapper mapper = (StudentMapper) ctx.getBean("studentMapper");
		Student student = mapper.getById(1);
		System.out.println(student.getGender());
		return new ModelAndView("/show","student",student);
	}
}
