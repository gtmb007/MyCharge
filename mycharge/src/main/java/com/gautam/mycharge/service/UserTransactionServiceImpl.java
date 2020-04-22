package com.gautam.mycharge.service;


import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gautam.mycharge.dao.UserTransactionDAO;
import com.gautam.mycharge.model.Plan;
import com.gautam.mycharge.model.Status;
import com.gautam.mycharge.model.Transaction;
import com.gautam.mycharge.model.User;

@Service(value="userTransactionService")
@Transactional
public class UserTransactionServiceImpl implements UserTransactionService{
	
	@Autowired
	private UserTransactionDAO userTransactionDAO;
	
	@Override
	public String addUsr(User usr) throws Exception {
		usr.setUsrBalance(0);
		usr.settList(new ArrayList<Transaction>());
		String usrId=userTransactionDAO.addUsr(usr);
		if(usrId==null) throw new Exception("Service.AlreadyRegistered");
		return usrId;
	}
	
	@Override
	public boolean validateUsr(User usr) throws Exception {
		User user=userTransactionDAO.getUsr(usr.getUsrId());
		if(user==null) throw new Exception("Service.NotRegistered");
		return usr.getPassword().equals(user.getPassword());
	}
	
	@Override
	public User getUsr(String usrId) throws Exception {
		User user=userTransactionDAO.getUsr(usrId);
		if(user==null) throw new Exception("Service.FailedToFetchDetails");
		return user;
	}
	
	@Override
	public String editUserName(String usrId, String newName) throws Exception {
		String s=userTransactionDAO.editUserName(usrId, newName);
		if(s==null) throw new Exception("Service.FailedToEditName");
		return s;
	}
	
	@Override
	public String changePassword(String usrId, String newPassword) throws Exception {
		String s=userTransactionDAO.changePassword(usrId, newPassword);
		if(s==null) throw new Exception("Service.FailedToChangePassword");
		return s;
	}
	
	@Override
	public double addWalletBalance(String usrId, Transaction t) throws Exception {
		Double walletBalance=userTransactionDAO.addWalletBalance(usrId, t);
		if(walletBalance==null) throw new Exception("Service.FailedToAddBalance");
		return walletBalance;
	}
	
	@Override
	public double recharge(String usrId, Plan plan) throws Exception {
		User user=userTransactionDAO.getUsr(usrId);
		double balance=user.getUsrBalance()-plan.getPlanAmount();
		if(balance<0) throw new Exception("Service.NotSufficientBalance");
		Transaction t=new Transaction();
		t.settAmount(plan.getPlanAmount());
		t.setStartTime(new Timestamp(System.currentTimeMillis()));
		t.setEndTime(new Timestamp(System.currentTimeMillis()));
		t.setStatus(Status.Debited);
		Double walletBalance=userTransactionDAO.recharge(usrId, t);
		if(walletBalance==null) throw new Exception("Service.FailedToRecharge");
		return walletBalance;
	}
	
}
