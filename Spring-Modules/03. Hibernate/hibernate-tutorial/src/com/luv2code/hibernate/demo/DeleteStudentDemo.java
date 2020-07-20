package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		try {
			int studentId = 1;
			
			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: "+studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			//Delete the student - first appraoch
//			System.out.println("Deleting student of id =1 ---> "+myStudent);
//			session.delete(myStudent);
			
			//Delete the student - second approach
			System.out.println("Deleting student with id=3");
			session.createQuery("delete from Student where id=3").executeUpdate();
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!!");
			
		} finally {
			factory.close();
		}
	}
}
