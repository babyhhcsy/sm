package com.github.pagehelper.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

/**
 * Description: MybatisHelper
 * Author: liuzh
 * Update: liuzh(2014-06-06 13:33)
 */
public class MybatisHelper {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //鍒涘缓SqlSessionFactory
            Reader reader = Resources.getResourceAsReader(TestUtil.getXmlPath() + "/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
            if (TestUtil.getXmlPath().equalsIgnoreCase("hsqldb")) {
                //鍒涘缓鏁版嵁搴�
                SqlSession session = null;
                try {
                    session = sqlSessionFactory.openSession();
                    Connection conn = session.getConnection();
                    reader = Resources.getResourceAsReader(TestUtil.getXmlPath() + "/" + TestUtil.getXmlPath() + ".sql");
                    ScriptRunner runner = new ScriptRunner(conn);
                    runner.setLogWriter(null);
                    runner.runScript(reader);
                    reader.close();
                } finally {
                    if (session != null) {
                        session.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 鑾峰彇Session
     *
     * @return
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
