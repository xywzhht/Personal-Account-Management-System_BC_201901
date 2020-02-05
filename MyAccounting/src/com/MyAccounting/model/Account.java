package com.MyAccounting.model;

public class Account {
	
	private int PID;//流水号
	private String category;//类别
	private String content;//内容
	private int money;//金额
	private String time;//日期
	
	
	public int getPId() {
		return this.PID;
	}
	
	public void setPId(int PID) {
		this.PID=PID;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public void setCategory(String category) {
		this.category=category;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content=content;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void setMoney(int money) {
		this.money=money;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public void setTime(String time) {
		this.time=time;
	}

}
