package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		try {
			//Create the objects
//			Instructor tempInstructor  = new Instructor("Anant", "J.", "15102052anant@gmail.com");
//			
//			InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.JavaDeveloper.com/youtube", "I love FSD");
			
			Instructor tempInstructor  = new Instructor("Arpita", "T.", "arpita.trivedi@gmail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.artisy.com/youtube", "I love creative designs");
			
			//Asociate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
		
			//start the transaction
			session.beginTransaction();
			
			//save the instructor
			System.out.println("Saving Instructor: "+tempInstructor);
			session.save(tempInstructor);
			//this will also save the details object because of CascadeType.ALL
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!!");
			
		} finally {
			factory.close();
		}
	}
}
