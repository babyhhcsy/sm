package com.tHero.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class SqlsessionFactoryGen {
	private static SqlSessionFactory factory;
	static {
		String resource = "mybatis-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		factory  = new SqlSessionFactoryBuilder().build(reader);
	}
	public static SqlSessionFactory getSqlsessionFactory(){
		return factory;
	}
}
