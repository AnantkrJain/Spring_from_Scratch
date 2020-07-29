package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	
	private  Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{
		
		//print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n======> Executing @Around on method : "+method);
		
		//get begin timestamp
		long begin = System.currentTimeMillis();
		
		//now execute the method
		Object result = theProceedingJoinPoint.proceed();
		
		//get end timestamp
		long end = System.currentTimeMillis();
		
		//compute duration and display it
		long duration = end-begin;
		myLogger.info("\nDuration :  " +duration/1000 +" seconds");
		
		return result;
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
			//print out which method we are advising on
			String method = theJoinPoint.getSignature().toShortString();
			myLogger.info("\n======> Executing @After (finally) on method : "+method);
	}
	
	@AfterThrowing(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", throwing="theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		
		//print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n======> Executing @AfterThrowing on method : "+method);
		
		//log the exception
		myLogger.info("\n======> the exception is : "+theExc);
	}
	
	@AfterReturning(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", returning="result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
		//print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n======> Executing @AfterReturning on method : "+method);
		
		//print out the results of the method call
		myLogger.info("\n======> result is : "+result);
		
		//let's post-process the data, let's modify it
		
		//convert the account name to uppercase
		convertAccountNamesToUpperCase(result);
		myLogger.info("\n\n====================> result is : "+result);
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
			myLogger.info("======> Executing @Before advice on addAccount()");
			
			//Display the method signature
			MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
			myLogger.info("Method :  "+methodSig);
			
			//display method arguments
			
			//get arguments
			Object[] args = theJoinPoint.getArgs();
			
			//loop through arguments
			for(Object tempArg : args) {
				myLogger.info(tempArg.toString());
				
				if(tempArg instanceof Account) {
					//downcast and print Account specific stuff
					Account theAccount = (Account) tempArg;
					
					myLogger.info("account name :  "+theAccount.getName());
					myLogger.info("account level :  "+theAccount.getLevel());
				}
			}
		}
}
