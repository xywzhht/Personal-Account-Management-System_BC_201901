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
import com.MyAccounting.dao.AccountDAO;
import com.MyAccounting.dao.*;
import com.MyAccounting.model.Account;
import com.MyAccounting.base.*;
/**
 * 模块说明： 添加学生
 * 
 */
public class AddView extends JFrame {

	private static final long serialVersionUID = -1984182788841566838L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton addButton, exitButton;
//	private JTextField name, sno, department, hometown, mark, email, tel, sex;
	private JTextField PID,category,content,money,time;

	public AddView() {
		init();
	}

	private void init() {
		setTitle(AppConstants.ADDVIEW_TITLE);
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
		time = new JTextField();
		jPanelCenter.add(time);
		
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel  南部按钮布局
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		addButton = new JButton(AppConstants.ADDVIEW_ADDBUTTON);
		addButton.addActionListener(new ActionListener() {
			@Override  //增加按钮
			public void actionPerformed(ActionEvent e) {
				if (check()) {    //若每一个框的内容都不为空，则执行
					Account acc = new Account();
					buildAccount(acc);
					boolean isSuccess = ((AccountDAO) BaseDAO.getAbilityDAO(DAO.AccountDAO)).add(acc);
					if (isSuccess) {
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
		jPanelSouth.add(addButton);
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
	
//id,category,content,money,time
	private void buildAccount(Account acc) {
		acc.setPId(Integer.parseInt(PID.getText()));
		acc.setCategory(category.getText());
		acc.setContent(content.getText());
		acc.setMoney(Integer.parseInt(money.getText()));
		acc.setTime(time.getText());
	}

	private void setEmpty() {
		PID.setText("");
		category.setText("");
		content.setText("");
		money.setText("");
		time.setText("");
	}
}
