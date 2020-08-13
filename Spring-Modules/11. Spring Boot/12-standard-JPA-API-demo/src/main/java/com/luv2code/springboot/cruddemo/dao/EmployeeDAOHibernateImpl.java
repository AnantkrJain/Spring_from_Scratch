package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO{

	//Define field for entity manager
	private EntityManager entityManager;
	
	//set up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		
		//Get the current Hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//Create a query
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		//Execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		//return the results
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		//Get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//Get the employee
		Employee theEmployee = currentSession.get(Employee.class, theId);
		
		//Return the employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		//Get the current Hibernate Session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//save Employee
		currentSession.saveOrUpdate(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		
		//Get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//Delete object with primary key
		Query<?> theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
	}
}


