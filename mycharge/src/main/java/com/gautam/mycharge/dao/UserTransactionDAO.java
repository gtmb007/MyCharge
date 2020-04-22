package com.gautam.mycharge.dao;

import com.gautam.mycharge.model.Transaction;
import com.gautam.mycharge.model.User;

public interface UserTransactionDAO {
	
	public String addUsr(User usr);
	public User getUsr(String usrId);
	public String editUserName(String usrId, String newName);
	public String changePassword(String usrId, String newPassword);
	public Double addWalletBalance(String userId, Transaction t);
	public Double recharge(String usrId, Transaction t);
	
}
