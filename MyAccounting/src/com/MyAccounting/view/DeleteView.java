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
 * 模块说明： 删除学生
 * 
 */
public class DeleteView extends JFrame {

	private static final long serialVersionUID = -7668153283910203959L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton deleteButton, exitButton;
	private JTextField category,content; // 根据类别+内容删除账本条目

	public DeleteView() {
		init();
	}

	private void init() {
		setTitle(AppConstants.DELETEVIEW_TITLE);
		// center panel 中央布局  类别和内容框
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(3, 2));
		jPanelCenter.add(new JLabel(AppConstants.ACCOUNT_CATEGORY));
		category = new JTextField();
		jPanelCenter.add(category);
		jPanelCenter.add(new JLabel(AppConstants.ACCOUNT_CONTENT));
		content = new JTextField();
		jPanelCenter.add(content);
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel  南部布局
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		deleteButton = new JButton(AppConstants.DELETEVIEW_DELETEBUTTON);
		deleteButton.addActionListener(new ActionListener() {
			@Override  //“删除”按钮事件
			public void actionPerformed(ActionEvent e) {
				if (check()) {   //不为空则进行此步
					Account acc = new Account();  //新建账本类对象
					buildAccount(acc);            //构造要删除的账本类对象
				//	boolean isSuccess = ((AccountDAO) BaseDAO.getAbilityDAO(DAO.AccountDAO)).add(acc);应该是打错了
					boolean isSuccess = ((AccountDAO) BaseDAO.getAbilityDAO(DAO.AccountDAO)).delete(acc);//更正，调用delete函数
					if (isSuccess) {      //若执行删除操作成功则
						setEmpty();       //两个框清空字眼
						if (MainView.currPageNum < 0 || MainView.currPageNum > 99) {
							MainView.currPageNum = 1;
						}
						String[][] result = ((AccountDAO) BaseDAO.getAbilityDAO(DAO.AccountDAO))
								.list(MainView.currPageNum);    //获取当前页面的条目到result数组中
						MainView.initJTable(MainView.jTable, result);    //调用mainView的函数将result数组的内容显示出来
					}
				}
			}
		});
		
		//按钮的布置
		jPanelSouth.add(deleteButton);
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
		setBounds(470, 250, 400, 130);
		setResizable(false);
		setVisible(true);
	}

	//用于检查 执行删除操作时，类别和内容框是否为空，都不为空则返回true值
	private boolean check() {
		boolean result = false;
		if ("".equals(category.getText()) || "".equals(content.getText())) {
			return result;        //若类别和内容框为空，则返回false
		} else {
			result = true;        //若不为空则返回ture
		}
		return result;
	}

	//以类别和内容构造一个账本对象acc出来 ，将acc传入delete函数执行删除操作
	private void buildAccount(Account acc) {
		acc.setCategory(category.getText());
		acc.setContent(content.getText());
	}

	//清空两个框的内容
	private void setEmpty() {
		category.setText("");
		content.setText("");
	}

}
