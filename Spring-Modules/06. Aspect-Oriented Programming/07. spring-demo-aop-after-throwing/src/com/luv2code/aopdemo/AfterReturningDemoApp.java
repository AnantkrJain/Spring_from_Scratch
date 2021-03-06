package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;

public class AfterReturningDemoApp {

	public static void main(String[] args) {
	
			//Read spring config java class
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
			//get the bean from spring container
			AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
			
			//Call method to find the accounts
			List<Account> theAccounts = theAccountDAO.findAccounts(false);
			
			//display the accounts
			System.out.println("\nMain Program : AfterReturningDemoApp");
			System.out.println("------------------");
			System.out.println(theAccounts+"\n");
		
			//close the context
			context.close();
	}

}
