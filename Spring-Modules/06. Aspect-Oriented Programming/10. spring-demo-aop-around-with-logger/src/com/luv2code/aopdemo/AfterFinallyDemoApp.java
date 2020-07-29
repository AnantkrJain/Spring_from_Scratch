package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;

public class AfterFinallyDemoApp {

	public static void main(String[] args) {
	
			//Read spring config java class
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
			//get the bean from spring container
			AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
			
			//Call method to find the accounts
			List<Account> theAccounts = null;
			try {
				//add  boolean flag to simulate exceptions
				boolean tripWire = false;
				theAccounts = theAccountDAO.findAccounts(tripWire);
			} catch(Exception exc) {
				System.out.println("\n\nMain Program . . . . . caught Exception "+exc);
			}
			
			//display the accounts
			System.out.println("\nMain Program : AfterThrowingDemoApp");
			System.out.println("------------------");
			System.out.println(theAccounts+"\n");
		
			//close the context
			context.close();
	}

}
