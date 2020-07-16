package com.udemy.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SwimJavaConfigDemoApp {
	public static void main(String[] args) {
		
		//read spring config java class
		AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext(SportConfig.class);
		
		//get the bean from spring container
		SwimCoach theCoach = (SwimCoach) context.getBean("swimCoach", Coach.class);
		
		//call a method on the bean
		System.out.println(theCoach.getDailyWorkout());
		//call method to get the use of costructor/setter/field injection
		System.out.println(theCoach.getDailyFortune());
		//call out new methods having properties values injected
		System.out.println(theCoach.getEmail());
		System.out.println(theCoach.getTeam());
				
		//close the context
		context.close();
	}
}
