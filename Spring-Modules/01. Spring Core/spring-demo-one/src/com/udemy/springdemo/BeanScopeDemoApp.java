package com.udemy.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {
	public static void main(String[] args) {
		
		//Load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
		
		//Retrieve bean from spring container
		Coach theCoach = context.getBean("myCoach", Coach.class);
		Coach alphaCoach =  context.getBean("myCoach", Coach.class);
		
		//check if they are same
		boolean result = (theCoach == alphaCoach);
		System.out.println("\nPointing to the same object  --> "+result);
		System.out.println("\nMemory Location for theCoach --> " +theCoach);
		System.out.println("\nMemory Location for alphaCoach --> "+alphaCoach);
		
		//close the context
		context.close();
	}
}

