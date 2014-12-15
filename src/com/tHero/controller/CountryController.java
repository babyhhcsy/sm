package com.tHero.controller;

import java.util.List;

import javax.print.attribute.standard.PageRanges;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tHero.domain.Student;
import com.tHero.domain.Teacher;
import com.tHero.mapper.StudentMapper;
import com.tHero.mapper.TeacherMapper;
import com.tHero.util.SpringContextUtil;

@Controller
@RequestMapping(value="/mutiMapper")
public class CountryController {
	@RequestMapping("/queryTeacher")
	public ModelAndView queryTeacherAndStudent(){
		TeacherMapper teacherMapper = (TeacherMapper) SpringContextUtil.getBean("teacherMapper");
		Teacher teacher = teacherMapper.getById(2);
		PageHelper.startPage(1,4);
		List<Student> list = teacher.getSupStudent();
		PageInfo<Student> page = new PageInfo<Student>(list);
		PageHelper.startPage(1, 2, true);
		page.setPageNum(2);
		System.out.println("****************************************");
		System.out.println(teacher.getId() + "  " + teacher.getName() + "  " + teacher.getResearchArea() +" "+ teacher.getGender());
		System.out.println("****************************************");
		List<Student> students = teacher.getSupStudent();
		for(int i = 0 ;i < students.size() ; i++){
			Student student = students.get(i);
			System.out.println(student.getId() + "" + student.getName() + " " +student.getMajor() + " " + student.getGender() + " " + student.getGrade());
		}
		return new ModelAndView("/show","student",students.get(0));
	}
	@RequestMapping("/queryStudent")
	public ModelAndView queryStudent() {
		StudentMapper studentMapper = (StudentMapper) SpringContextUtil.getBean("studentMapper");
		PageHelper.startPage(1, 7);
		List<Student> students = studentMapper.queryAll();
		return new ModelAndView("/show","student",students.get(0));
	}
}
