package com.tHero.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.tHero.domain.Student;
import com.tHero.domain.Teacher;
import com.tHero.mapper.StudentMapper;
import com.tHero.util.SpringContextUtil;


public class SpringTransaction implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		StudentMapper mapper =  (StudentMapper) SpringContextUtil.getBean("studentMapper");
		PlatformTransactionManager tm = SpringContextUtil.getPlatformTransactionManager();
		Student student = new Student();
		student.setName("����");
		student.setGender("Ů");
		student.setMajor("�������ѧ�뼼��");
		student.setGender("2");
		student.setGrade("100");
		Teacher supervisor = new Teacher();
		supervisor.setId(1);
		student.setSupervisor(supervisor);
		//TransactionDefinition ����������Ķ��壬����Ĵ�����Ϊ����ǰ��������propagation_requiredֻ�������ԣ�
		TransactionDefinition def = new DefaultTransactionDefinition();
		//���󷵻���������
		TransactionStatus status = tm.getTransaction(def);
		try {
			mapper.addStudent(student);
		} catch (Exception e) {
			tm.rollback(status);
			e.printStackTrace();
		}finally{
			tm.commit(status);
		}
		return new ModelAndView("/show","student","����ɹ�");
	}

}
