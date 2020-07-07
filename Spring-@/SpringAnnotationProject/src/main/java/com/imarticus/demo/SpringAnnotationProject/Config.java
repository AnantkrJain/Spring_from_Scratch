package com.imarticus.demo.SpringAnnotationProject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config {

	@Bean
		public Employee employee() {
			System.out.println("Employee Bean");
			return new Employee(10);
		}
	
	@Bean
	@Primary
	@Qualifier("main")
	public Department department() {
		System.out.println("Department Bean");
		return new Department();
	}
}
