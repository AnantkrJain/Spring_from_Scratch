package com.udemy.springdemo;

public class MyApp {
	public static void main(String[] args) {
		
		//create the object
		Coach theCoach1 = new BaseballCoach();
		
		//use the object
		System.out.println(theCoach1.getDailyWorkout());
		
		Coach theCoach2 = new TrackCoach();
		System.out.println(theCoach2.getDailyWorkout());
	}
}
