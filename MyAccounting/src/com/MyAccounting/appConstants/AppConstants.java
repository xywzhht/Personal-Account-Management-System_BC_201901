package com.MyAccounting.appConstants;

/**
 * 项目名：student
 * 修改历史：
 * 作者： MZ
 * 创建时间： 2016年1月6日-上午9:41:57
 */


/**
 * 模块说明： 用来储存需要用到的常量
 * 
 */
public class AppConstants {
	// jdbc
//	public static final String JDBC_URL = "jdbc:sqlite:test.db";
	public static final String JDBC_URL ="jdbc:mysql://localhost:3306/Account";
	public static final String JDBC_USERNAME = "root";
	public static final String JDBC_PASSWORD = "l3226287222";
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	// account field
	public static final String ACCOUNT_ID = "序号";
	public static final String ACCOUNT_PID = "流水号";
	public static final String ACCOUNT_CATEGORY = "类别";
	public static final String ACCOUNT_CONTENT = "内容";
	public static final String ACCOUNT_MONEY = "金额";
	public static final String ACCOUNT_TIME = "日期";

	// login view
	public static final String LOGIN_TITLE = "登录界面";
	public static final String LOGIN_USERNAME = "用户名";
	public static final String LOGIN_PASSWORD = "密码";
	public static final String LOGIN = "登录";
	public static final String RESET = "重置";

	// main view
	public static final String MAINVIEW_TITLE = "个人记账理财管理系统";
	public static final String MAINVIEW_PAGENUM_JLABEL_DI = "第 ";
	public static final String MAINVIEW_PAGENUM_JLABEL_YE = "/99 页";
	public static final String MAINVIEW_FIND_JLABEL = "查询结果";
	public static final String MAINVIEW_FIRST = "首页";
	public static final String MAINVIEW_LAST = "末页";
	public static final String MAINVIEW_PRE = "上一页";
	public static final String MAINVIEW_NEXT = "下一页";
	public static final String PARAM_FIND_CONDITION = "";
	public static final String PARAM_FIND = "查找";
	public static final String PARAM_ADD = "添加";
	public static final String PARAM_DELETE = "删除";
	public static final String PARAM_UPDATE = "更新";
	public static final String PARAM_COUNT = "统计";

	// add view
	public static final String ADDVIEW_TITLE = "添加账本条目";
	public static final String ADDVIEW_ADDBUTTON = "添加";
	public static final String EXITBUTTON = "退出";

	// delete view
	public static final String DELETEVIEW_TITLE = "删除账本条目";
	public static final String DELETEVIEW_DELETEBUTTON = "删除";

	// update view
	public static final String UPDATEVIEW_TITLE = "更新账本条目";
	public static final String UPDATEVIEW_UPDATEBUTTON = "更新";
	
	//count view
	public static final String COUNT_TITLE = "统计结果";
	public static final String COUNT_OUTPUT = "总支出";
	public static final String COUNT_INPUT = "总收入";
	

}
