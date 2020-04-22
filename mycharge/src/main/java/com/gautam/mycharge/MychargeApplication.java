package com.gautam.mycharge;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.gautam.mycharge.model.Plan;
import com.gautam.mycharge.model.Status;
import com.gautam.mycharge.model.Transaction;
import com.gautam.mycharge.model.User;
import com.gautam.mycharge.service.UserTransactionService;

@SpringBootApplication
public class MychargeApplication implements CommandLineRunner {

	@Autowired
	private UserTransactionService userTransactionService;
	
	@Autowired
	private Environment environment;
	
	private Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		SpringApplication.run(MychargeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		while(true) {
			System.out.println("\nPlease Enter...\n1.) SignUp\n2.) SignIn\n0.) Exit");
			int opt=sc.nextInt();
			if(opt==0) return;
			else if(opt==1) signUp();
			else if(opt==2) signIn();
			else System.out.println("\n"+environment.getProperty("UI.InvalidOption"));
		}
	}
	
	public void signUp() {
		User usr=new User();
		System.out.print("\nEnter User Id: ");
		usr.setUsrId(sc.next());
		System.out.print("Enter User Name: ");
		usr.setUsrName(sc.next());
		System.out.print("Enter Password: ");
		usr.setPassword(sc.next());
		try {
			String s=userTransactionService.addUsr(usr);
			System.out.println("\n"+s+" SignUp successfully!");
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void signIn() {
		User usr=new User();
		System.out.print("\nEnter User Id: ");
		usr.setUsrId(sc.next());
		System.out.print("Enter Password: ");
		usr.setPassword(sc.next());
		try {
			if(userTransactionService.validateUsr(usr)) {
				System.out.println("\n"+usr.getUsrId()+" SignIn successfully!");
				while(true) {
					System.out.println("\nPlease Enter...\n1.) User Details\n2.) Edit User Name\n3.) Change Password\n4.) Add Wallet Balance\n5.) Recharge\n0.) Log Out");
					int opt=sc.nextInt();
					if(opt==0) return;
					else if(opt==1) getUserDetails(usr.getUsrId());
					else if(opt==2) editUserName(usr.getUsrId());
					else if(opt==3) changePassword(usr.getUsrId());
					else if(opt==4) addWalletBalance(usr.getUsrId());
					else if(opt==5) recharge(usr.getUsrId());
					else System.out.println("\n"+environment.getProperty("UI.InvalidOption"));
				}
			} else {
				System.out.println("\n"+environment.getProperty("UI.InvalidCredentials"));
			}
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void getUserDetails(String usrId) {
		try {
			System.out.println("\nUser Details...\n");
			User usr=userTransactionService.getUsr(usrId);
			System.out.println("User Id: "+usr.getUsrId());
			System.out.println("User Name: "+usr.getUsrName());
			System.out.println("Wallet Balance: "+usr.getUsrBalance());
			System.out.println("\nList of All Transactions...\n");
			List<Transaction> listOfTransaction=usr.gettList();
			if(listOfTransaction.isEmpty()) System.out.println("There is no transaction.");
			else {
				for(Transaction t : listOfTransaction) {
					System.out.println(t.gettId()+"\t"+t.gettAmount()+"\t"+t.getStartTime()+"\t"+t.getEndTime()+"\t"+t.getStatus());
				}
			}
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void editUserName(String usrId) {
		try {
			System.out.print("\nEnter New User Name: ");
			String newName=sc.next();
			String s=userTransactionService.editUserName(usrId, newName);
			System.out.println("\n"+s+" User Name updated successfully!");
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void changePassword(String usrId) {
		try {
			System.out.print("\nEnter New Password: ");
			String newPassword=sc.next();
			String s=userTransactionService.changePassword(usrId, newPassword);
			System.out.println("\n"+s+" Password updated successfully!");
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void addWalletBalance(String usrId) {
		Transaction t=new Transaction();
		System.out.print("\nEnter amount: ");
		t.settAmount(sc.nextDouble());
		t.setStartTime(new Timestamp(System.currentTimeMillis()));
		t.setEndTime(new Timestamp(System.currentTimeMillis()));
		t.setStatus(Status.Credited);
		try {
			double usrBalance=userTransactionService.addWalletBalance(usrId, t);
			System.out.println("\nBalance added successfully\nUpdated Wallet Balance: "+usrBalance);
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
	public void recharge(String usrId) {
		Plan plan=new Plan();
		System.out.print("\nEnter Plan Name: ");
		plan.setPlanName(sc.next());
		System.out.print("Enter Plan Amount: ");
		plan.setPlanAmount(sc.nextDouble());
		try {
			double usrBalance=userTransactionService.recharge(usrId, plan);
			System.out.println("\nRecharged successfully\nUpdated Wallet Balance: "+usrBalance);
		} catch(Exception e) {
			System.out.println("\n"+environment.getProperty(e.getMessage()));
		}
	}
	
}
