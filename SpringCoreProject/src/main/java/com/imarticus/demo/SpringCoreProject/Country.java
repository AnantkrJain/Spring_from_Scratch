package com.imarticus.demo.SpringCoreProject;

public class Country<city> {
	private City city;

	public Country() {
		super();
		System.out.println("Country Constructor");
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	public void visitCountry() {
		System.out.println("travel to the country");
	}
	
	public void travel() {
		this.visitCountry();
		city.visitCity();
	}
}
