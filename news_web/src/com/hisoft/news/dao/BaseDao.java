package com.hisoft.news.dao;

import com.hisoft.news.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BaseDao {

    protected Connection conn;

    public BaseDao(Connection conn) {
        this.conn = conn;
    }

    /* static {
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
    }*/

    //增删改操作
    public int update(String sql, Object... params) {
        int result = 0;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(null, pstmt, null);
        }
        return result;
    }



}
