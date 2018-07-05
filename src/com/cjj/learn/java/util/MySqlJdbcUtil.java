package com.cjj.learn.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * oracle 连接类
 * @author chenjianjun
 *
 */
public class MySqlJdbcUtil {
	
	private static String driver = "com.mysql.jdbc.Driver";
	
	private static String url = "jdbc:mysql://localhost:3306/test";
	
	private static String user = "root";
	
	private static String password = "root";
	
	//注册数据库驱动
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//获取数据库连接
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
		return null;
	}
	
	//释放数据库资源
	public static void release(Connection conn, Statement st, ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;  //java 垃圾回收机制
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				st = null;
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
	}
	
	public static void main(String args[]) {
		Connection conn = getConnection();
		try {
			System.out.println(conn.isValid(10));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn,null,null);
		}
	}

}
