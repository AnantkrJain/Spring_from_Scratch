package com.imarticus.demo.SpringCoreProject;

import java.util.List;

public class Department {
	private int did;
	private String name;
	private List<String> functions;
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(int did, String name, List<String> functions) {
		super();
		this.did = did;
		this.name = name;
		this.functions = functions;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getFunctions() {
		return functions;
	}

	public void setFunctions(List<String> functions) {
		this.functions = functions;
	}
 
	@Override
	public String toString() {
		return "Department [did=" + did + ", name=" + name + ", functions=" + functions + "]";
	}
}
