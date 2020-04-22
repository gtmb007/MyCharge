package com.gautam.mycharge;


import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import com.gautam.mycharge.dao.UserTransactionDAO;
import com.gautam.mycharge.model.Plan;
import com.gautam.mycharge.model.Transaction;
import com.gautam.mycharge.model.User;
import com.gautam.mycharge.service.UserTransactionService;
import com.gautam.mycharge.service.UserTransactionServiceImpl;

//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MychargeApplicationTests {
	
	@Mock
	private UserTransactionDAO userTransactionDAO;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@InjectMocks
	private UserTransactionService userTransactionService = new UserTransactionServiceImpl();

//	@Autowired
//	private UserTransactionService userTransactionService;
	
	@Test
	public void addUsrValidCredentials() throws Exception {
		User user=new User();
		user.setUsrId("__gangaa");
		user.setUsrName("Gangamma");
		user.setPassword("ganga@1997");
		when(userTransactionDAO.addUsr(user)).thenReturn(user.getUsrId());
		String actual = userTransactionService.addUsr(user);
		Assert.assertEquals(user.getUsrId(), actual);
	}
	
	@Test
	public void addUsrInValidCredentials() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Service.AlreadyRegistered");
		
		User user=new User();
		user.setUsrId("gtmb007");
		user.setUsrName("Gautam");
		user.setPassword("gtmb@1998");
		when(userTransactionDAO.addUsr(user)).thenReturn(null);
		userTransactionService.addUsr(user);
	}
	
	@Test
	public void validateUsrValidCredentials() throws Exception {
		User user=new User();
		user.setUsrId("gtmb007");
		user.setPassword("gtmb@1998");
		when(userTransactionDAO.getUsr(user.getUsrId())).thenReturn(user);
		boolean actual = userTransactionService.validateUsr(user);
		Assert.assertEquals(true, actual);
	}
	
	@Test
	public void validateUsrInValidCredentials() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Service.NotRegistered");
		User user=new User();
		user.setUsrId("__gangaa");
		user.setPassword("ganga@1997");
		when(userTransactionDAO.getUsr(user.getUsrId())).thenReturn(null);
		userTransactionService.validateUsr(user);
	}
	
	
	
	@Test
	public void getUsrValidCredentials() throws Exception {
		User user=new User();
		user.setUsrId("gtmb007");
		when(userTransactionDAO.getUsr(user.getUsrId())).thenReturn(user);
		User actual = userTransactionService.getUsr(user.getUsrId());
		Assert.assertEquals(user.getUsrId(), actual.getUsrId());
	}
	
	@Test
	public void getUsrInValidCredentials() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Service.FailedToFetchDetails");
		User user=new User();
		user.setUsrId("__gangaa");
		when(userTransactionDAO.getUsr(user.getUsrId())).thenReturn(null);
		userTransactionService.getUsr(user.getUsrId());
	}
	
	@Test
	public void editUserNameValidCredentials() throws Exception {
		String usrId="gtmb007";
		String newUsrName="Bharadwaj";
		when(userTransactionDAO.editUserName(usrId, newUsrName)).thenReturn(usrId);
		String actual = userTransactionService.editUserName(usrId, newUsrName);
		Assert.assertEquals(usrId, actual);
	}
	
	@Test
	public void editUserNameInValidCredentials() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Service.FailedToEditName");
		String usrId="__gangaa";
		String newUsrName="Goudar";
		when(userTransactionDAO.editUserName(usrId, newUsrName)).thenReturn(null);
		userTransactionService.editUserName(usrId, newUsrName);
	}
	
	@Test
	public void changePasswordValidCredentials() throws Exception {
		String usrId="gtmb007";
		String newPassword="gautam@123";
		when(userTransactionDAO.changePassword(usrId, newPassword)).thenReturn(usrId);
		String actual = userTransactionService.changePassword(usrId, newPassword);
		Assert.assertEquals(usrId, actual);
	}
	
	@Test
	public void changePasswordInValidCredentials() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Service.FailedToChangePassword");
		String usrId="__gangaa";
		String newPassword="ganga@123";
		when(userTransactionDAO.changePassword(usrId, newPassword)).thenReturn(null);
		userTransactionService.changePassword(usrId, newPassword);
	}
	
	@Test
	public void addWalletBalanceValidCredentials() throws Exception {
		User user=new User();
		user.setUsrId("gtmb007");
		user.setUsrBalance(1000);
		Transaction t=new Transaction();
		t.settAmount(500);
		Double balance=user.getUsrBalance()+t.gettAmount();
		when(userTransactionDAO.addWalletBalance(user.getUsrId(), t)).thenReturn(balance);
		Double actual = userTransactionService.addWalletBalance(user.getUsrId(), t);
		Assert.assertEquals(balance, actual);
	}
	
	@Test
	public void addWalletBalanceInValidCredentials() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Service.FailedToAddBalance");
		User user=new User();
		user.setUsrId("__gangaa");
		user.setUsrBalance(1000);
		Transaction t=new Transaction();
		t.settAmount(500);
//		Double balance=user.getUsrBalance()+t.gettAmount();
		when(userTransactionDAO.addWalletBalance(user.getUsrId(), t)).thenReturn(null);
		userTransactionService.addWalletBalance(user.getUsrId(), t);
	}
	
	@Test
	public void rechargeInsufficientBalance() throws Exception {
		expectedException.expect(Exception.class);
		expectedException.expectMessage("Service.NotSufficientBalance");
		User user=new User();
		user.setUsrId("gtmb007");
		user.setUsrBalance(1000);
		Plan plan=new Plan();
		plan.setPlanAmount(1500);
		Transaction t=new Transaction();
		t.settAmount(plan.getPlanAmount());
//		Double balance=user.getUsrBalance()-t.gettAmount();
		when(userTransactionDAO.getUsr(user.getUsrId())).thenReturn(user);
//		when(userTransactionDAO.recharge(user.getUsrId(), t)).thenReturn(balance);
		userTransactionService.recharge(user.getUsrId(), plan);
	}
	
//	@Test
//	public void rechargeValidCredentials() throws Exception {
//		User user=new User();
//		user.setUsrId("gtmb007");
//		user.setUsrBalance(1000);
//		Plan plan=new Plan();
//		plan.setPlanAmount(500);
//		Transaction t=new Transaction();
//		t.settAmount(plan.getPlanAmount());
//		Double balance=user.getUsrBalance()-t.gettAmount();
//		when(userTransactionDAO.getUsr(user.getUsrId())).thenReturn(user);
//		when(userTransactionDAO.recharge(user.getUsrId(), t)).thenReturn(balance);
//		Double actual = userTransactionService.recharge(user.getUsrId(), plan);
//		Assert.assertEquals(balance, actual);
//	}
//	
//	@Test
//	public void rechargeInValidCredentials() throws Exception {
//		expectedException.expect(Exception.class);
//		expectedException.expectMessage("Service.FailedToRecharge");
//		User user=new User();
//		user.setUsrId("__gangaa");
//		user.setUsrBalance(1000);
//		Plan plan=new Plan();
//		plan.setPlanAmount(500);
//		Transaction t=new Transaction();
//		t.settAmount(plan.getPlanAmount());
////		Double balance=user.getUsrBalance()-t.gettAmount();
//		when(userTransactionDAO.getUsr(user.getUsrId())).thenReturn(user);
//		when(userTransactionDAO.recharge(user.getUsrId(), t)).thenReturn(null);
//		userTransactionService.recharge(user.getUsrId(), plan);
//	}
	
}
