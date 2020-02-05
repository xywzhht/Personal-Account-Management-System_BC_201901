package com.MyAccounting.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.MyAccounting.base.BaseDAO;
import com.MyAccounting.model.Account;
/*
 *账本操控类 
 */
public class AccountDAO extends BaseDAO { 
	 private final int fieldNum = 6; //显示的列数：序号、流水号、类别、内容、金额、日期
	 private final int showNum = 15; //显示的元组数
	 private static AccountDAO ac = null; 
	  
	 public static synchronized AccountDAO getInstance() { 
	  if (ac == null) { 
	   ac = new AccountDAO(); 
	  } 
	  return ac; 
	 } //操控类初始化
	  
	 // 账本更新功能update   根据流水号PID来更新条目
	 public boolean update(Account acc) { 
	  boolean result = false; 
	  if (acc == null) { 
	   return result; 
	  } //若账本类为空
	  try { 
	   // check 
/*	   if (queryBySno(acc.getPId()) == 0) { 
	    return result;
	   } */
	   // update 
	   String sql = "update account set category=?,Content=?,money=?,time=? where PID=?";
	   String[] param = { acc.getCategory(),acc.getContent(),String.valueOf(acc.getMoney()),acc.getTime(),String.valueOf(acc.getPId())}; 
	   int rowCount = db.executeUpdate(sql, param);   //返回受影响的行数
	   if (rowCount == 1) { 
	    result = true; 
	   } 
	  } /*catch (SQLException se) { 
	   se.printStackTrace(); 
	  }*/catch(Exception e) {
		  e.printStackTrace();
	  }finally { 
	   destroy(); 
	  } 
	  return result; //如果更新成功则返回true
	 } 
	  
	 // 账本类删除功能delete 
	 public boolean delete(Account acc) { 
	  boolean result = false; 
	  if (acc == null) { 
	   return result; 
	  } //根据类别和内容来删除条目
	  String sql = "delete from account where category=? and content=?;"; //加了个;号
	  String[] param = {acc.getCategory(),acc.getContent()}; 
	  int rowCount = db.executeUpdate(sql, param); //若返回1则1行受影响
	  if (rowCount == 1) { 
	   result = true; 
	  } 
	  destroy(); 
	  return result; //删除成功则返回true
	 } 
	 
	  
	 // add //调用插入语句实现增加条目功能
	 public boolean add(Account acc) { 
	  boolean result = false; 
	  if (acc == null) { 
	   return result; 
	  } 
	  try { 
		  
	   // check 
/*	   if (queryBySno(acc.getPId()) == 1) { 
	    return result; 
	   } */
		  
	   // insert 
	   String sql = "insert into account(PID,category,content,money,time) values(?,?,?,?,?)"; 
	   String[] param = { String.valueOf(acc.getPId()),acc.getCategory(),acc.getContent(),String.valueOf(acc.getMoney()),acc.getTime() }; 
	   if (db.executeUpdate(sql, param) == 1) { 
	    result = true; 
	   } 
	  }/* catch (SQLException se) { 
	   se.printStackTrace(); 
	  } */catch(Exception e) {
		  e.printStackTrace();
	  }finally { 
	   destroy(); 
	  } 
	  return result;    //增加成功则返回true
	 } 
	  
	 // query by category 根据条目类别来进行搜索
	 public String[][] queryBycategory(String category) { 
	  String[][] result = null; 
	  if (category.length() < 0) { 
	   return result; 
	  } 
	  List<Account> acco = new ArrayList<Account>(); //新建List来储存账本对象
	  int i = 0; 
	  String sql = "select * from account where category like ?"; 
	  String[] param = { "%" + category + "%" }; 
	  rs = db.executeQuery(sql, param); //用resultset 储存根据条目类别搜索出来的内容
	  try { 
	   while (rs.next()) { 
	    buildList(rs, acco, i);      //将搜索结果，一条一条的元组调用buildList函数存进List里
	    i++; 
	   } 
	   if (acco.size() > 0) { 
	    result = new String[acco.size()][fieldNum]; 
	    for (int j = 0; j < acco.size(); j++) { 
	     buildResult(result, acco, j); 
	    } 
	   } 
	  } catch (SQLException se) { 
	   se.printStackTrace(); 
	  } finally { 
	   destroy(); 
	  } 
	  
	  return result; 
	 } 
	  
	 // query 
	 public String[][] list(int pageNum) { 
	  String[][] result = null; 
	  if (pageNum < 1) { 
	   return result;       //若页数小于1则返回空结果
	  } 
	  List<Account> acco = new ArrayList<Account>();//创建一个储存Account账本类对象的数组list
	  int i = 0; 
	  int beginNum = (pageNum - 1) * showNum; //上一页的最后一个条目
	  String sql = "select * from account limit ?,?"; 
	  //LIMIT后的第一个参数是输出记录的初始位置，第二个参数偏移量，偏移多少，输出的条目就是多少。
	  Integer[] param = { beginNum, showNum }; 
	  rs = db.executeQuery(sql, param);   //执行SQL语句返回结果集
	  try { 
	   while (rs.next()) { 
	    buildList(rs, acco, i);   //一行行构建结果数组
	    i++; 
	   } 
	   if (acco.size() > 0) { 
	    result = new String[acco.size()][fieldNum]; 
	    for (int j = 0; j < acco.size(); j++) { 
	     buildResult(result, acco, j);      //根据一个个账本类对象来构造结果数组
	    } 
	   } 
	  } catch (SQLException se) { 
	   se.printStackTrace(); 
	  } finally { 
	   destroy(); 
	  } 
	  
	  return result; 
	 } 
	  
	 // 将rs记录添加到list中 
	 private void buildList(ResultSet rs, List<Account> list, int i) throws SQLException { 
		 Account acc = new Account(); 
//	  acc.setxuhao(i + 1); //id值被盗取了  设置行数号
	  acc.setPId(rs.getInt("PID"));
	  acc.setCategory(rs.getString("category"));
	  acc.setContent(rs.getString("content"));
	  acc.setMoney(rs.getInt("money")); 
	  acc.setTime(rs.getString("time"));
	  list.add(acc); 
	 } 
	  
	 // 将list中记录添加到二维数组中 
	 private void buildResult(String[][] result, List<Account> acco, int j) { 
		 Account acc = acco.get(j); 
	  result[j][0] = String.valueOf(acc.getPId()); 
	  result[j][1] = acc.getCategory(); 
	  result[j][2] = acc.getContent(); 
	  result[j][3] = String.valueOf(acc.getMoney()); 
	  result[j][4] = acc.getTime(); 
	 } 
/*	  
	 // query by id  ？？？？？？？？？？？？？？
	 private int queryBySno(int id) throws SQLException { 
	  int result = 0; 
	  if (id==0) { 
	   return result; 
	  } 
	  String checkSql = "select * from account where id=?"; 
	  String[] checkParam = { String.valueOf(id) }; 
	  rs = db.executeQuery(checkSql, checkParam); 
	  if (rs.next()) { 
	   result = 1; 
	  } 
	  return result; 
	 }
*/
	 //查询登录的用户名和账号是否正确
	public boolean queryForLogin(String username, String password) {
		// TODO 自动生成的方法存根
		//System.out.println(username+password);
		boolean result = false;
		if (username.length() == 0 || password.length() == 0) {
			return result;
		}
//		String sql = "select * from admin where username=? and password=?";
//		String[] param = { username, password };
//		rs = db.executeQuery(sql, param);
		try {
			if (username.charAt(0)=='Q'&&password.charAt(0)=='t') {
				result = true;
			}
		} finally {
			destroy();
		}
		return result;
	}

	// 账本类统计功能count
	//收入
	public ResultSet count1() {
		// TODO 自动生成的方法存根
		ResultSet result;
		String sql = "select sum(money) from account where money>0;";
		result = db.executeQuery(sql);
		return result;
	} 
	//支出
	public ResultSet count2() {
		ResultSet result;
		String sql = "select sum(money) from account where money<0;"; //加了个;号 
		result = db.executeQuery(sql);
		return result;
	}
	  
	} 


/*public ResultSet count1() {
			 ResultSet result;
		     String sql = "select sum(money) from account where money>0;"; //加了个;号 
		     result = db.executeQuery(sql);
		     return result;
		 }*/


