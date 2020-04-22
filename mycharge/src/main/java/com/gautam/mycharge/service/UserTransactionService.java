package com.gautam.mycharge.service;

import com.gautam.mycharge.model.Plan;
import com.gautam.mycharge.model.Transaction;
import com.gautam.mycharge.model.User;

public interface UserTransactionService {
	
	public String addUsr(User usr) throws Exception;
	public boolean validateUsr(User usr) throws Exception;
	public User getUsr(String usrId) throws Exception;
	public String editUserName(String usrId, String newName) throws Exception;
	public String changePassword(String usrId, String newPassword) throws Exception;
	public double addWalletBalance(String userId, Transaction t) throws Exception;
	public double recharge(String usrId, Plan plan) throws Exception;

}
