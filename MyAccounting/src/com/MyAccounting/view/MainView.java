package com.MyAccounting.view;
/*view：与用户交互的界面（包括LoginView.java、MainView.java、
 * AddView.java、DeleteView.java、UpdateView.java），主要使用DAO提供的接口*/

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


import com.MyAccounting.appConstants.*;
import com.MyAccounting.dao.*;
import com.MyAccounting.base.*;

/**
 * 模块说明： 首页
 * 
 */
public class MainView extends JFrame {

	private static final long serialVersionUID = 5870864087464173884L;

	private final int maxPageNum = 99;//最大页数

	private JPanel jPanelNorth, jPanelSouth, jPanelCenter;
	private JButton jButtonFirst, jButtonLast, jButtonNext, jButtonPre, jButtonAdd, jButtonDelete, jButtonUpdate,
			jButtonFind,jButtoncount;//统计按钮
	private JLabel currPageNumJLabel;
	private JTextField condition;
	public static JTable jTable;
	private JScrollPane jScrollPane;
	private DefaultTableModel myTableModel;

	public static String[] column = {AppConstants.ACCOUNT_PID,AppConstants.ACCOUNT_CATEGORY, AppConstants.ACCOUNT_CONTENT,
			AppConstants.ACCOUNT_MONEY, AppConstants.ACCOUNT_TIME };
	
	/*public static String[] column = { "id", AppConstants.ACCOUNT_CATEGORY, AppConstants.ACCOUNT_CONTENT,
			AppConstants.ACCOUNT_MONEY, AppConstants.ACCOUNT_TIME };*/
	public static int currPageNum = 1;

	public MainView() {
		init();
	}

	private void init() {
		setTitle(AppConstants.MAINVIEW_TITLE);

		// north panel   布局北部分
		jPanelNorth = new JPanel();
		jPanelNorth.setLayout(new GridLayout(1, 6));
		condition = new JTextField(AppConstants.PARAM_FIND_CONDITION);
		condition.addKeyListener(new FindListener());   //查询类别的输入框
		jPanelNorth.add(condition);
		
		// query by category  根据条目类别查询  “查找”按钮
		jButtonFind = new JButton(AppConstants.PARAM_FIND);
		jButtonFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				find();                     //调用find函数进行查询
			}
		});
		jButtonFind.addKeyListener(new FindListener());
		
		// add   增加条目功能   "添加"按钮
		jPanelNorth.add(jButtonFind);
		jButtonAdd = new JButton(AppConstants.PARAM_ADD);
		jButtonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddView();  //打开增加条目界面
			}
		});
		jPanelNorth.add(jButtonAdd);
		
		// delete   删除条目功能   "删除"按钮
		jButtonDelete = new JButton(AppConstants.PARAM_DELETE);
		jButtonDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteView();  //打开删除条目界面
			}
		});
		jPanelNorth.add(jButtonDelete);
		
		// update  更新条目功能  "更新"按钮
		jButtonUpdate = new JButton(AppConstants.PARAM_UPDATE);
		jButtonUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateView();   //打开更新条目界面
			}
		});
		jPanelNorth.add(jButtonUpdate);
		
		//count 统计条目功能 “统计”按钮
		jButtoncount = new JButton(AppConstants.PARAM_COUNT);
		jButtoncount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CountView();   //打开更新条目界面
			}
		});
		jPanelNorth.add(jButtoncount);
		

		// center panel 中部布局
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(1, 1));

		// init jTable    显示表
//		String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).list(currPageNum);
		String[][] result = ((AccountDAO)BaseDAO.getAbilityDAO(DAO.AccountDAO)).list(currPageNum);
		myTableModel = new DefaultTableModel(result, column);
		jTable = new JTable(myTableModel);
		
		//设置文本的水平对齐方式
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		jTable.setDefaultRenderer(Object.class, cr);
		initJTable(jTable, result);

		jScrollPane = new JScrollPane(jTable);
		jPanelCenter.add(jScrollPane);

		// south panel   南部布局
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 5));

		//首页按钮
		jButtonFirst = new JButton(AppConstants.MAINVIEW_FIRST);
		jButtonFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPageNum = 1;//回到第1页
				String[][] result = ((AccountDAO)BaseDAO.getAbilityDAO(DAO.AccountDAO)).list(currPageNum);
				initJTable(jTable, result);//重新显示第1页的数据
				currPageNumJLabel.setText(AppConstants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum
						+ AppConstants.MAINVIEW_PAGENUM_JLABEL_YE);//显示第1页
			}
		});
		//上一页的按钮
		jButtonPre = new JButton(AppConstants.MAINVIEW_PRE);
		jButtonPre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currPageNum--;
				if (currPageNum <= 0) {
					currPageNum = 1;
				}//重新显示该页表的数据
				String[][] result = ((AccountDAO)BaseDAO.getAbilityDAO(DAO.AccountDAO)).list(currPageNum);
				initJTable(jTable, result);
				currPageNumJLabel.setText(AppConstants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum
						+ AppConstants.MAINVIEW_PAGENUM_JLABEL_YE);
			}
		});
		//下一页的按钮
		jButtonNext = new JButton(AppConstants.MAINVIEW_NEXT);
		jButtonNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPageNum++;
				if (currPageNum > maxPageNum) {
					currPageNum = maxPageNum;
				}//重新显示该页表的数据
				String[][] result = ((AccountDAO)BaseDAO.getAbilityDAO(DAO.AccountDAO)).list(currPageNum);
				initJTable(jTable, result);
				currPageNumJLabel.setText(AppConstants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum
						+ AppConstants.MAINVIEW_PAGENUM_JLABEL_YE);
			}
		});
		//跳转到最后一页
		jButtonLast = new JButton(AppConstants.MAINVIEW_LAST);
		jButtonLast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPageNum = maxPageNum;
				//重新显示该页表的数据
				String[][] result = ((AccountDAO)BaseDAO.getAbilityDAO(DAO.AccountDAO)).list(currPageNum);
				initJTable(jTable, result);
				currPageNumJLabel.setText(AppConstants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum
						+ AppConstants.MAINVIEW_PAGENUM_JLABEL_YE);
			}
		});

		//第X页 中间对齐 设置多个按钮
		currPageNumJLabel = new JLabel(
				AppConstants.MAINVIEW_PAGENUM_JLABEL_DI + currPageNum + AppConstants.MAINVIEW_PAGENUM_JLABEL_YE);
		currPageNumJLabel.setHorizontalAlignment(JLabel.CENTER);

		jPanelSouth.add(jButtonFirst);
		jPanelSouth.add(jButtonPre);
		jPanelSouth.add(currPageNumJLabel);
		jPanelSouth.add(jButtonNext);
		jPanelSouth.add(jButtonLast);

		this.add(jPanelNorth, BorderLayout.NORTH);
		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		setBounds(400, 200, 750, 340);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	//将数据显示到表中
	public static void initJTable(JTable jTable, String[][] result) {
		((DefaultTableModel) jTable.getModel()).setDataVector(result, column);
		//设置元组的高度
		jTable.setRowHeight(30);
		
		//流水号列
		TableColumn secondColumn = jTable.getColumnModel().getColumn(0);
		secondColumn.setPreferredWidth(110);
		secondColumn.setMaxWidth(110);
		secondColumn.setMinWidth(110);
		//类别列
		TableColumn thirdColumn = jTable.getColumnModel().getColumn(1);
		thirdColumn.setPreferredWidth(110);
		thirdColumn.setMaxWidth(110);
		thirdColumn.setMinWidth(110);
		//内容列
		TableColumn fourthColumn = jTable.getColumnModel().getColumn(2);
		fourthColumn.setPreferredWidth(180);
		fourthColumn.setMaxWidth(180);
		fourthColumn.setMinWidth(180);
		//金额列
		TableColumn fiveColumn = jTable.getColumnModel().getColumn(3);
		fiveColumn.setPreferredWidth(100);
		fiveColumn.setMaxWidth(100);
		fiveColumn.setMinWidth(100);
		//日期列
		TableColumn sixColumn = jTable.getColumnModel().getColumn(4);
		sixColumn.setPreferredWidth(230);
		sixColumn.setMaxWidth(230);
		sixColumn.setMinWidth(230);
	/*	//序号列
		TableColumn firsetColumn = jTable.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(50);
		firsetColumn.setMaxWidth(50);
		firsetColumn.setMinWidth(50);
		//流水号列
		TableColumn secondColumn = jTable.getColumnModel().getColumn(1);
		secondColumn.setPreferredWidth(110);
		secondColumn.setMaxWidth(110);
		secondColumn.setMinWidth(110);
		//类别列
		TableColumn thirdColumn = jTable.getColumnModel().getColumn(2);
		thirdColumn.setPreferredWidth(70);
		thirdColumn.setMaxWidth(70);
		thirdColumn.setMinWidth(70);
		//内容列
		TableColumn fourthColumn = jTable.getColumnModel().getColumn(3);
		fourthColumn.setPreferredWidth(180);
		fourthColumn.setMaxWidth(180);
		fourthColumn.setMinWidth(180);
		//金额列
		TableColumn fiveColumn = jTable.getColumnModel().getColumn(4);
		fiveColumn.setPreferredWidth(80);
		fiveColumn.setMaxWidth(80);
		fiveColumn.setMinWidth(80);
		//日期列
		TableColumn sixColumn = jTable.getColumnModel().getColumn(5);
		sixColumn.setPreferredWidth(230);
		sixColumn.setMaxWidth(230);
		sixColumn.setMinWidth(230);*/
	}

	private class FindListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				find();        //键盘按下回车时候触发读取内容，调用find函数
			}
		}
	}

	//查询功能“根据条目类别”
	private void find() {
		currPageNum = 1;
		String param = condition.getText();   //读取查询类别的输入框的内容
		if ("".equals(param) || param == null) {
			initJTable(MainView.jTable, null);//若为空白内容则输入空表，什么都不显示，“查询结果”为空
			currPageNumJLabel.setText(AppConstants.MAINVIEW_FIND_JLABEL);
			return;
		}
		String[][] result = ((AccountDAO) BaseDAO.getAbilityDAO(DAO.AccountDAO)).queryBycategory(param);
//		String[][] result = ((AccountDAO)BaseDAO.getAbilityDAO(DAO.AccountDAO)).list(currPageNum);
		condition.setText("");           //查询类别清空
		initJTable(MainView.jTable, result);     //根据条目的类别显示查询结果
		currPageNumJLabel.setText(AppConstants.MAINVIEW_FIND_JLABEL);
	}

}
