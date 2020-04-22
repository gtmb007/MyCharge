package com.gautam.mycharge.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="User")
public class UserEntity {
	
	@Id
	@Column(name="usr_id")
	private String usrId;
	
	@Column(name="usr_name")
	private String usrName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="usr_balance")
	private double usrBalance;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="usr_id")
	private List<TransactionEntity> tList;

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

	public List<TransactionEntity> gettList() {
		return tList;
	}

	public void settList(List<TransactionEntity> tList) {
		this.tList = tList;
	}
	
}
