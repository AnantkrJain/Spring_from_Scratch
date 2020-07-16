package com.udemy.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component("thatSillyCoach")
@Component
//@Scope("prototype")
public class TennisCoach implements Coach {
	
	@Autowired
	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;
	
	@Value("${foo.email}")
	private String email;
	
	@Value("${foo.team}")
	private String team;
	
	//Define a default constructor
	public TennisCoach() {
		System.out.println(">> TennisCoach:  inside default constructor");
	}
	
	//define my init method
	@PostConstruct
	public void doMyStartupStuff() {
		System.out.println(">> TennisCoach:  inside doMyStartupStuff()");
	}
	
	//define my destroy method
	@PreDestroy
		public void doMyCleanupStuff() {
			System.out.println(">> TennisCoach:  inside doMyCleanupStuff()");
		}
		
	/*
	//Spring will use any method for dependency injection, setter method is not compulsory
	@Autowired
	public void doSomeStuff(FortuneService fortuneService) {
		System.out.println(">> TennisCoach:  inside doSomeStuff() method");
		this.fortuneService = fortuneService;
	}
	
	//define a setter method
	@Autowired
	public void setFortuneService(FortuneService fortuneService) {
		System.out.println(">> TennisCoach:  inside setFortuneService() method");
		this.fortuneService = fortuneService;
	}

	@Autowired
	public TennisCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	*/

	@Override
	public String getDailyWorkout() {
		return "Practice your backhand vollley";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	public String getEmail() {
		return email;
	}

	public String getTeam() {
		return team;
	}
}
