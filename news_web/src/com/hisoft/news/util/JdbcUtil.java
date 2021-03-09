package com.hisoft.news.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.hisoft.news.dao.BaseDao;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-19 16:12:43
 **/
public class JdbcUtil {
    private static DataSource dataSource;

    /*static{
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/news");
            System.out.println("连接池初始化完成");
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }*/

    static {
        InputStream is = null;
        try {
            // 创建Properties类对象
            Properties properties = new Properties();
            // 读取properties属性文件到输入流中
            is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
            // 从输入流中加载属性列表
            properties.load(is);

            dataSource = DruidDataSourceFactory.createDataSource(properties);

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        // 获取数据库连接对象
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            System.out.println("获取连接"+conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                System.out.println("\t归还连接"+conn);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
