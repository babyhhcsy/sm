package com.tHero.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.MybatisReasonableHelper;
import com.tHero.domain.Student;
import com.tHero.domain.Teacher;
import com.tHero.mapper.StudentMapper;
import com.tHero.mapper.TeacherMapper;
import com.tHero.util.SpringContextUtil;

public class PageTest {
	@Test
	public void testTeacher() {
		 SqlSession sqlSession = MybatisReasonableHelper.getSqlSession();
		 StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
		 PageHelper.startPage(1, 7);
		 List<Student> students = studentMapper.queryAll();
		 PageInfo<Student> page = new PageInfo<Student>(students);
		/* assertEquals(4, students.size());
         assertEquals(3, page.getStartRow());
         assertEquals(4, page.getPageNum());
         assertEquals(3, page.getTotal());*/
	}
	   /**
     * 使用Mapper接口调用时，使用PageHelper.startPage效果更好，不需要添加Mapper接口参数
     */
    @Test
    public void testMapperWithStartPage() {
    	TeacherMapper teacherMapper = (TeacherMapper) SpringContextUtil.getBean("teacherMapper");
		Teacher teacher = teacherMapper.getById(2);
		PageHelper.startPage(1,4);
		List<Student> list = teacher.getSupStudent();
		PageInfo<Student> page = new PageInfo<Student>(list);
        try {
            //获取第20页，2条内容
            //分页插件会自动改为查询最后一页
            PageHelper.startPage(20, 50);
           // List<Country> list = countryMapper.selectAll();
            //PageInfo page = new PageInfo(list);
            assertEquals(33, list.size());
            assertEquals(151, page.getStartRow());
            assertEquals(4, page.getPageNum());
            assertEquals(183, page.getTotal());

            //获取第-3页，2条内容
            //由于只有7天数据，分页插件会自动改为查询最后一页
            PageHelper.startPage(-3, 50);
           // list = countryMapper.selectAll();
            page = new PageInfo(list);
            assertEquals(50, list.size());
            assertEquals(1, page.getStartRow());
            assertEquals(1, page.getPageNum());
            assertEquals(183, page.getTotal());
        } finally {
          //  sqlSession.close();
        }
    }
}
