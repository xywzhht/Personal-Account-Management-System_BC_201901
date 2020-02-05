package com.MyAccounting.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.MyAccounting.util.*;
import com.MyAccounting.dao.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.MyAccounting.appConstants.AppConstants;
import com.MyAccounting.base.BaseDAO;
import com.MyAccounting.model.Account;

public class CountView extends JFrame{

	private JPanel jPanelCenter;
	private JTextField output;
	private JTextField input;
	private static DBUtil db;
	
	
	public CountView() {
		// TODO 自动生成的构造函数存根
		init();
		power();
	}

	private void power() {
		// TODO 自动生成的方法存根
		ResultSet result1,result2;
		//Account acc = new Account();
		result1=((AccountDAO) BaseDAO.getAbilityDAO(DAO.AccountDAO)).count1();
		result2=((AccountDAO) BaseDAO.getAbilityDAO(DAO.AccountDAO)).count2();
/*		db.getDBUtil();
		db.getConn();
		String sql = "select sum(money) from account where money>0;";
		result1 = db.executeQuery(sql);*/
		try {
			while(result1.next()) {
				input.setText(result1.getString(1));
			}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			while(result2.next()) {
				output.setText(result2.getString(1));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

	private void init() {
		// TODO 自动生成的方法存根
		setTitle(AppConstants.COUNT_TITLE);//设置标题
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(2, 2));
		
		jPanelCenter.add(new JLabel(AppConstants.COUNT_OUTPUT));
		output = new JTextField();
		jPanelCenter.add(output);
		
		jPanelCenter.add(new JLabel(AppConstants.COUNT_INPUT));
		input = new JTextField();
		jPanelCenter.add(input);
		
		this.add(jPanelCenter, BorderLayout.CENTER);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(470, 200, 400, 270);
		setResizable(false);
		setVisible(true);
	}
}
