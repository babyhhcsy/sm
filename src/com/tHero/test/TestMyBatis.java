package com.tHero.test;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.tHero.domain.Student;
import com.tHero.mapper.StudentMapper;
import com.tHero.util.SqlsessionFactoryGen;


public class TestMyBatis {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	@Test
	public void testStudent(){
		String resource = "mybatis-config.xml";
		Reader reader = null;
		try{
			reader = Resources.getResourceAsReader(resource);
		}catch(IOException e){
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			Student student = studentMapper.getById(1);
			if(student!=null){
				System.out.println("姓名：" + student.getName() + "\n 专业：" + student.getMajor() );
			}else{
				System.out.println("没有找到该学生的相关信息！");
			}
		}finally{
			sqlSession.close();
		}
	}
	@Test
	public void insertStudent(){
		SqlSessionFactory factory = SqlsessionFactoryGen.getSqlsessionFactory();
		SqlSession sqlSession =  factory.openSession();
		StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
		Student student = new Student();
		student.setGender("0");
		student.setGrade("100");
		student.setMajor("软件技术");
		student.setName("王震");
		int effectRow  = studentMapper.add(student );
		sqlSession.commit();
		System.out.println(effectRow+""+student.getId());
	}
	@Test
	public void updateStudent(){
		SqlSessionFactory factory = SqlsessionFactoryGen.getSqlsessionFactory();
		SqlSession sqlSession = factory.openSession();
		StudentMapper studentMappper = sqlSession.getMapper(StudentMapper.class);
		Student student = new Student();
		student.setId(3);
		student.setGender("男");
		int effectRow = studentMappper.update(student);
		sqlSession.commit();
		System.out.println(effectRow);
	}
	@Test
	public void delteStudent(){
		SqlSessionFactory factory = SqlsessionFactoryGen.getSqlsessionFactory();
		SqlSession sqlSession = factory.openSession();
		StudentMapper studentMappper = sqlSession.getMapper(StudentMapper.class);
		Student student = new Student();
		student.setId(3);
		int effectRow = studentMappper.delete(student);
		sqlSession.commit();
		System.out.println(effectRow);
	}
	@Test
	public void testStudentAndTeacher(){
		SqlSessionFactory factory = SqlsessionFactoryGen.getSqlsessionFactory();
		SqlSession sqlSession = factory.openSession();
		StudentMapper studentMappper = sqlSession.getMapper(StudentMapper.class);
		Student s = studentMappper.getStudnetAndTeacher(1);
		System.out.println(s.getMajor() + "----" + s.getGrade());
		System.out.println(s.getSupervisor().getName()+ "?????");
	}
	@Test
	public void test1s(){
			String name = "3M#DP-190";
			String[] splitName = name.split(" ");
			String[] s = new String[2];
			if(splitName.length<=2){
				s = splitName;
			}else{
				s[0] = splitName[splitName.length-1];
				s[1] = splitName[splitName.length-2];
			}
			if(splitName.length<=2){
				System.out.println(s[0]);
			}else{
				System.out.println(s[0]+"--"+s[1]);
				
			}
		}
}






