package com.gautam.mycharge.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.gautam.mycharge.entity.TransactionEntity;
import com.gautam.mycharge.entity.UserEntity;
import com.gautam.mycharge.model.Transaction;
import com.gautam.mycharge.model.User;

@Repository(value="userTransactionDAO")
public class UserTransactionDAOImpl implements UserTransactionDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String addUsr(User usr) {
		UserEntity usrEntity=entityManager.find(UserEntity.class, usr.getUsrId());
		if(usrEntity!=null) return null;
		else {
			usrEntity=new UserEntity();
			usrEntity.setUsrId(usr.getUsrId());
			usrEntity.setUsrName(usr.getUsrName());
			usrEntity.setPassword(usr.getPassword());
			usrEntity.setUsrBalance(usr.getUsrBalance());
			List<Transaction> tList=usr.gettList();
			List<TransactionEntity> tEntityList=new ArrayList<TransactionEntity>();
			for(Transaction t : tList) {
				TransactionEntity tEntity=new TransactionEntity();
				tEntity.settAmount(t.gettAmount());
				tEntity.setStartTime(t.getStartTime());
				tEntity.setEndTime(t.getEndTime());
				tEntity.setStatus(t.getStatus());
				tEntityList.add(tEntity);
			}
			usrEntity.settList(tEntityList);
			entityManager.persist(usrEntity);
			return usrEntity.getUsrId();
		}
	}
	
	@Override
	public User getUsr(String usrId) {
		UserEntity usrEntity=entityManager.find(UserEntity.class, usrId);
		User usr=null;
		if(usrEntity!=null) {
			usr=new User();
			usr.setUsrId(usrEntity.getUsrId());
			usr.setUsrName(usrEntity.getUsrName());
			usr.setPassword(usrEntity.getPassword());
			usr.setUsrBalance(usrEntity.getUsrBalance());
			List<TransactionEntity> tEntityList=usrEntity.gettList();
			List<Transaction> tList=new ArrayList<Transaction>();
			for(TransactionEntity tEntity : tEntityList) {
				Transaction t=new Transaction();
				t.settId(tEntity.gettId());
				t.settAmount(tEntity.gettAmount());
				t.setStartTime(tEntity.getStartTime());
				t.setEndTime(tEntity.getEndTime());
				t.setStatus(tEntity.getStatus());
				tList.add(t);
			}
			usr.settList(tList);
		}
		return usr;
	}
	
	@Override
	public String editUserName(String usrId, String newName) {
		UserEntity usrEntity=entityManager.find(UserEntity.class, usrId);
		if(usrEntity!=null) {
			usrEntity.setUsrName(newName);
			return usrEntity.getUsrId();
		}
		return null;
	}
	
	@Override
	public String changePassword(String usrId, String newPassword) {
		UserEntity usrEntity=entityManager.find(UserEntity.class, usrId);
		if(usrEntity!=null) {
			usrEntity.setPassword(newPassword);
			return usrEntity.getUsrId();
		}
		return null;
	}
	
	@Override
	public Double addWalletBalance(String usrId, Transaction t) {
		UserEntity usrEntity=entityManager.find(UserEntity.class, usrId);
		if(usrEntity!=null) {
			usrEntity.setUsrBalance(usrEntity.getUsrBalance()+t.gettAmount());
			List<TransactionEntity> tEntityList=usrEntity.gettList();
			TransactionEntity tEntity=new TransactionEntity();
			tEntity.settAmount(t.gettAmount());
			tEntity.setStartTime(t.getStartTime());
			tEntity.setEndTime(t.getEndTime());
			tEntity.setStatus(t.getStatus());
			tEntityList.add(tEntity);
			return usrEntity.getUsrBalance();
		} 
		return null;
	}
	
	@Override
	public Double recharge(String usrId, Transaction t) {
		UserEntity usrEntity=entityManager.find(UserEntity.class, usrId);
		if(usrEntity!=null) {
			usrEntity.setUsrBalance(usrEntity.getUsrBalance()-t.gettAmount());
			List<TransactionEntity> tEntityList=usrEntity.gettList();
			TransactionEntity tEntity=new TransactionEntity();
			tEntity.settAmount(t.gettAmount());
			tEntity.setStartTime(t.getStartTime());
			tEntity.setEndTime(t.getEndTime());
			tEntity.setStatus(t.getStatus());
			tEntityList.add(tEntity);
			return usrEntity.getUsrBalance();
		}
		return null;
	}
	
}
