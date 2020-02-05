package com.MyAccounting.base;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.MyAccounting.dao.AccountDAO;
import com.MyAccounting.dao.DAO;
import com.MyAccounting.util.DBUtil;
/*
 * 账本操控类的基类
 */
public abstract class BaseDAO { 
	 protected final DBUtil db = DBUtil.getDBUtil(); 
	 protected ResultSet rs; 
	 private static BaseDAO baseDAO; 
	  
	 public BaseDAO() { 
	  init(); 
	 } 
	  
	 private void init() { 
	  // buildAbilityDAO(); 
	 } 
	  
	 // protected abstract void buildAbilityDAO(); 
	  
	 //连接数据库
	 public static synchronized BaseDAO getAbilityDAO(DAO dao) { 
	  switch (dao) { 
	  case AccountDAO: 
	   if (baseDAO == null || baseDAO.getClass() != AccountDAO.class) { 
	    baseDAO = AccountDAO.getInstance(); 
	   } 
	   break; 
	  default: 
	   break; 
	  } 
	  return baseDAO; 
	 } 
	  
	 protected void destroy() {      //清理resultset并且关闭数据库
	  try { 
	   if (rs != null) { 
	    rs.close(); 
	   } 
	  } catch (SQLException se) { 
	   se.printStackTrace(); 
	  } finally { 
	   db.close(); 
	  } 
	 } 
	} 
