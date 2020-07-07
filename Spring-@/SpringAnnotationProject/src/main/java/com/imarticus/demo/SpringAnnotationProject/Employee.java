package com.imarticus.demo.SpringAnnotationProject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import org.springframework.stereotype.Component;

@Component
public class Employee {

	public List<String> str;
	
	public int id;
	public Department department;
	
	@Autowired
	public Employee(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@SuppressWarnings("deprecation")
	@Required
	public void setId(int id) {
		this.id = id;
	}
  
	public void works() {
		System.out.println("Employee is working");
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", department=" + department + "]";
	}
}
