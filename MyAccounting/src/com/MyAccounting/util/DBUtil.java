package com.MyAccounting.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.MyAccounting.appConstants.AppConstants;

//工具类DBUtil（对jdbc进行封装）
public class DBUtil {

	private static DBUtil db; 
	  
	 private Connection conn; 
	 private PreparedStatement ps; 
	 private ResultSet rs; 
	  
	 private DBUtil() { 
	  
	 } 
	   
	 //创建工具类对象db
	 public static DBUtil getDBUtil() {
		if(db == null) {
			db = new DBUtil();
		}
		return db;
	}
	  
	 public int executeUpdate(String sql) {
		 int result = -1;
		 if(getConn() == null) {
			 return result;
		 }
		 try {
			 ps = conn.prepareStatement(sql); 
			 result = ps.executeUpdate(); //执行SQL语句，返回受影响的行数
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	  return result; 
	 } 
	 
	 public int executeUpdate(String sql,Object[] obj) {
		int result = -1;
		if(getConn()==null) {
			return result;//判断数据库是否已连接成功
		}
		try {
			ps = conn.prepareStatement(sql);
			for(int i=0;i<obj.length;i++) {//执行SQL语句，多行受影响
				ps.setObject(i + 1, obj[i]);
			}
			result = ps.executeUpdate(); //返回受影响的行数
			close(); 
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	 
	 public ResultSet executeQuery(String sql) {
		if(getConn() == null) {
			return null;
		}
		try {
			ps = conn.prepareStatement(sql); //执行SQL语句
			rs = ps.executeQuery();          //返回SQL执行的结果集
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	 
	 public ResultSet executeQuery(String sql, Object[] obj) { 
		  if (getConn() == null) { 
		   return null; 
		  } 
		  try { 
		   ps = conn.prepareStatement(sql); //多行执行SQL语句
		   for (int i = 0; i < obj.length; i++) { 
		    ps.setObject(i + 1, obj[i]); 
		   } 
		   rs = ps.executeQuery();        //返回执行的结果集
		  } catch (SQLException e) { 
		   e.printStackTrace(); 
		  } 
		  
		  return rs; 
		} 
	 
	 /*1>方法executeQuery用于产生单个结果集（ResultSet）的语句，例如 SELECT 语句。
	  *  被使用最多的执行 SQL 语句的方法。这个方法被用来执行 SELECT 语句，它几乎是使用最多的 SQL 语句。
	  *  但也只能执行查询语句，执行后返回代表查询结果的ResultSet对象*/
	 
	 /*2>方法executeUpdate用于执行 INSERT、UPDATE 或 DELETE 语句以及 SQL DDL（数据定义语言）语句，
	  * 例如 CREATE TABLE 和 DROP TABLE。INSERT、UPDATE 或 DELETE 语句的效果是修改表中零行或多行中
	  * 的一列或多列。executeUpdate 的返回值是一个整数（int），指示受影响的行数（即更新计数）。
	  * 对于 CREATE TABLE 或 DROP TABLE 等不操作行的语句，executeUpdate 的返回值总为零。*/
	 
	 //真正连接数据库
	 public Connection getConn() {
		 try {
			 System.out.println("马上就连接成功了");
			 if(conn == null||conn.isClosed()) {
				 Class.forName(AppConstants.JDBC_DRIVER);
				 conn= DriverManager.getConnection(AppConstants.JDBC_URL, AppConstants.JDBC_USERNAME, 
						 AppConstants.JDBC_PASSWORD); 
				 System.out.println("连接成功");
			 }
		 }catch (ClassNotFoundException e) {
			// TODO: handle exception
			 System.out.println("jdbc driver is not found.");
			 e.printStackTrace();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 return conn;
		
	}

/*	 public Connection getConn() {
		 try {
			 if(conn == null||conn.isClosed()) {
				 Class.forName("com.mysql.jdbc.Driver");
				 conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Account","root", 
						 "l3226287222"); 
				 System.out.println("连接成功");
			 }
		 }catch (ClassNotFoundException e) {
			// TODO: handle exception
			 System.out.println("jdbc driver is not found.");
			 e.printStackTrace();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 return conn;
		
	}*/
	 //关闭数据库连接
	 public void close() {
		try {
			if(rs !=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(conn!=null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
