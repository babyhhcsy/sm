package com.tHero.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.tHero.domain.Student;
import com.tHero.mapper.StudentMapper;

public class SpringAndMybatis extends AbstractJUnit4SpringContextTests{
	@Test
	public void testSpringAndMybatisConfig(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentMapper mapper = (StudentMapper) ctx.getBean("studentMapper");
		Student student = mapper.getById(1);
		System.out.println(student.getGender());
	}
}

