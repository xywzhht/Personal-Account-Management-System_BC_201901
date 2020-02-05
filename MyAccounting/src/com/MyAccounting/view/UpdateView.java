package com.MyAccounting.view;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.MyAccounting.appConstants.*;
import com.MyAccounting.dao.*;
import com.MyAccounting.base.*;
import com.MyAccounting.model.*;

/**
 * 模块说明： 更新学生信息
 * 
 */
public class UpdateView extends JFrame {

	private static final long serialVersionUID = 5292738820127102731L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton updateButton, exitButton;
//	private JTextField name, sno, department, hometown, mark, email, tel, sex;
	private JTextField PID,category,content,money,time;

	public UpdateView() {
		init();
	}

	//更新窗口 构造函数
	private void init() {
		setTitle(AppConstants.UPDATEVIEW_TITLE);//设置标题
		// center panel 中央布局
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(6, 2));
		
		jPanelCenter.add(new JLabel(AppConstants.ACCOUNT_PID));
		PID = new JTextField();
		jPanelCenter.add(PID);
		
		jPanelCenter.add(new JLabel(AppConstants.ACCOUNT_CATEGORY));
		category = new JTextField();
		jPanelCenter.add(category);
		
		jPanelCenter.add(new JLabel(AppConstants.ACCOUNT_CONTENT));
		content = new JTextField();
		jPanelCenter.add(content);
		jPanelCenter.add(new JLabel(AppConstants.ACCOUNT_MONEY));
		money = new JTextField();
		jPanelCenter.add(money);
		jPanelCenter.add(new JLabel(AppConstants.ACCOUNT_TIME));
		time  = new JTextField();
		jPanelCenter.add(time);
		
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel 南部按钮布局
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		updateButton = new JButton(AppConstants.UPDATEVIEW_UPDATEBUTTON);
		updateButton.addActionListener(new ActionListener() {
			@Override   //更新按钮
			public void actionPerformed(ActionEvent e) {
				if (check()) {  //若每一个框的内容都不为空，则执行
					Account acc = new Account();
					buildAccount(acc);
					boolean isSuccess = ((AccountDAO) BaseDAO.getAbilityDAO(DAO.AccountDAO)).update(acc);
					if (isSuccess) {  //若更新成功
						setEmpty();
						if (MainView.currPageNum < 0 || MainView.currPageNum > 99) {
							MainView.currPageNum = 1;
						}
						String[][] result = ((AccountDAO) BaseDAO.getAbilityDAO(DAO.AccountDAO))
								.list(MainView.currPageNum);
						MainView.initJTable(MainView.jTable, result);
					}
				}
			}
		});
		jPanelSouth.add(updateButton);
		exitButton = new JButton(AppConstants.EXITBUTTON);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jPanelSouth.add(exitButton);

		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(470, 200, 400, 270);
		setResizable(false);
		setVisible(true);
	}

	//检查若每一个框的内容都不为空，则返回true
	private boolean check() {
		boolean result = false;
		if ("".equals(PID.getText()) || "".equals(category.getText()) || "".equals(content.getText())
				|| "".equals(money.getText()) || "".equals(time.getText())) {
			return result;
		} else {
			result = true;
		}
		return result;
	}

	//读取各文本框的内容构造一个账本对象acc出来 ，将acc传入add函数执行增加条目操作
	private void buildAccount(Account acc) {
		acc.setPId(Integer.parseInt(PID.getText()));  //String 转成int
		acc.setCategory(category.getText());
		acc.setContent(content.getText());
		acc.setMoney(Integer.parseInt(money.getText()));  //String 转成int
		acc.setTime(time.getText());
	}

	//将内容框清空
	private void setEmpty() {
		PID.setText("");
		category.setText("");
		content.setText("");
		money.setText("");
		time.setText("");
	}
}

