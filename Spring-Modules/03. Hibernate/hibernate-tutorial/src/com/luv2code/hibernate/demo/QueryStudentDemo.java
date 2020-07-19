package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		try {
			
			//start the transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
		
			//display the students
			displayStudents(theStudents);
			
			
			//query students: last name: 'Jain'
			theStudents = session.createQuery("from Student s where s.firstName='Anant Kumar' ").getResultList();
			
			//display the students
			System.out.println("\n\nStudents who has last name of Jain");
			displayStudents(theStudents);
			
		
			//query students: last name: 'Jain' OR first name: 'Anant'
			theStudents = session.createQuery("from Student s where s.lastName='Jain' OR s.firstName='Anant' ").getResultList();
		
			//display the students
			System.out.println("\n\nStudents who has last name of Jain or first name of Anant");
			displayStudents(theStudents);
			
			
			//query students: where email ends with 'developer@gmail.com'
			theStudents = session.createQuery("from Student s where s.email LIKE '%developer@gmail.com' ").getResultList();
			
			//display the students
			System.out.println("\n\nStudents whose email ends with developer@gmail.com");
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!!");
			
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}
}
