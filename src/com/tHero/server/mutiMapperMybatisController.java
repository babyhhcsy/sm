package com.tHero.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.tHero.domain.Student;
import com.tHero.domain.Teacher;
import com.tHero.mapper.StudentMapper;
import com.tHero.mapper.TeacherMapper;
import com.tHero.util.SpringContextUtil;


public class mutiMapperMybatisController implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView modelAndView = this.queryTeacherAndStudent();
		return modelAndView;
	}
	public ModelAndView queryTeacherAndStudent(){
		TeacherMapper teacherMapper = (TeacherMapper) SpringContextUtil.getBean("teacherMapper");
		Teacher teacher = teacherMapper.getById(2);
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
	/**
	 * ������ʦ
	 * ע�⣺Mapperֻ��ִ���Ѿ�������xml�е�sql�ļ��������Զ���ȥ���ݿ��ڲ����Ӧ�Ĺ�����ϵ
	 * @return
	 */
	public ModelAndView insertTeacher(){
		TeacherMapper teacherMapper = (TeacherMapper) SpringContextUtil.getBean("teacherMapper");
		StudentMapper studentMapper = (StudentMapper) SpringContextUtil.getBean("studentMapper");
		Teacher teacher = new Teacher();
		teacher.setId(3);
		teacher.setName("����");
		teacher.setGender("Ů");
		teacher.setResearchArea("��������");
		teacher.setTitle("����");
		
		Student student = new Student();
		student.setId(121);
		student.setGender("��");
		student.setName("������");
		
		student.setSupervisor(teacher);
		studentMapper.addStudent(student);
		
		List<Student> supStudent = new ArrayList<Student>();
		supStudent.add(student);
		
		teacher.setSupStudent(supStudent );
		return new ModelAndView("/show","student",student);
	}
	
	/**
	 * ����ѧ��
	 * @return
	 */
	public ModelAndView insertStudent(){
		StudentMapper mapper = (StudentMapper) SpringContextUtil.getBean("studentMapper");
		Student student = new Student();
		student.setName("����1");
		student.setGender("��");
		student.setMajor("�������ѧ�뼼��");
		student.setGrade("2011");
		student.setId(123);
		
		Teacher supervisor = new Teacher();
		supervisor.setId(2);
		student.setSupervisor(supervisor);
		
		
		List<Student> students = new ArrayList<Student>();
		students.add(student);
		
		supervisor.setSupStudent(students);;
		mapper.addStudent(student);
		return new ModelAndView("/show","student",student);
		
	}
}
