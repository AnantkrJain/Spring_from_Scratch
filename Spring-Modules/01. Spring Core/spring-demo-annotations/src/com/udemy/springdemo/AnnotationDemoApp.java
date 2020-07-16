package com.udemy.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {
	public static void main(String[] args) {
		
		//read spring config file
		ClassPathXmlApplicationContext context  = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//get the bean from spring container
		TennisCoach theCoach = (TennisCoach) context.getBean("tennisCoach", Coach.class);
		
		//call a method on the bean
		System.out.println(theCoach.getDailyWorkout());
		//call method to get the use of costructor/setter/field injection
		System.out.println(theCoach.getDailyFortune());
		//call out new methods to inject the properties values
		System.out.println(theCoach.getEmail());
		System.out.println(theCoach.getTeam());
		
		//close the context
		context.close();
	}
}
