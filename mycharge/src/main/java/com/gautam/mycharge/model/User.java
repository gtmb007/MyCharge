package com.gautam.mycharge.model;

import java.util.List;

public class User {
	
	private String usrId;
	private String usrName;
	private String password;
	private double usrBalance;
	private List<Transaction> tList;
	
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getUsrBalance() {
		return usrBalance;
	}
	public void setUsrBalance(double usrBalance) {
		this.usrBalance = usrBalance;
	}
	public List<Transaction> gettList() {
		return tList;
	}
	public void settList(List<Transaction> tList) {
		this.tList = tList;
	}
	
}
