package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
			//print out which method we are advising on
			String method = theJoinPoint.getSignature().toShortString();
			System.out.println("\n======> Executing @After (finally) on method : "+method);
	}
	
	@AfterThrowing(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", throwing="theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		
		//print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n======> Executing @AfterThrowing on method : "+method);
		
		//log the exception
		System.out.println("\n======> the exception is : "+theExc);
	}
	
	@AfterReturning(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", returning="result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
		//print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n======> Executing @AfterReturning on method : "+method);
		
		//print out the results of the method call
		System.out.println("\n======> result is : "+result);
		
		//let's post-process the data, let's modify it
		
		//convert the account name to uppercase
		convertAccountNamesToUpperCase(result);
		System.out.println("\n\n====================> result is : "+result);
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {
	
		// loop thorugh accounts
		for (Account tempAccount : result) {
			
			//get uppercase version of account names
			String theUpperName = tempAccount.getName().toUpperCase();
			
			//update the name on account
			tempAccount.setName(theUpperName);
		}
	}

	//This is where we add all of our related advices for logging
		//let's start with an @Before advice
		
		@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
		public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
			System.out.println("======> Executing @Before advice on addAccount()");
			
			//Display the method signature
			MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
			System.out.println("Method :  "+methodSig);
			
			//display method arguments
			
			//get arguments
			Object[] args = theJoinPoint.getArgs();
			
			//loop through arguments
			for(Object tempArg : args) {
				System.out.println(tempArg);
				
				if(tempArg instanceof Account) {
					//downcast and print Account specific stuff
					Account theAccount = (Account) tempArg;
					
					System.out.println("account name :  "+theAccount.getName());
					System.out.println("account level :  "+theAccount.getLevel());
				}
			}
		}
}
