package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class AddCoursesForArpitaDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
													.configure("hibernate.cfg.xml")
													.addAnnotatedClass(Instructor.class)
													.addAnnotatedClass(InstructorDetail.class)
													.addAnnotatedClass(Course.class)
													.addAnnotatedClass(Review.class)
													.addAnnotatedClass(Student.class)
													.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		try {
		
			//start the transaction
			session.beginTransaction();
			
			//Create a course
			Course tempCourse = new Course("Violin - ABC's of violin");
			
			//save the course and leverage the cascade all
			System.out.println("Saving the course");
			session.save(tempCourse);
			System.out.println("Saved the course:  "+tempCourse);
			
			//Create the students
			Student tempStudent1 = new Student("Anant", "Jain", "anantkr.developer@gmail.com");
			Student tempStudent2 = new Student("Arpita", "Trivedi", "arpita.trivedi@gmail.com");
			
			//add students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			System.out.println("Saved students :  "+tempCourse.getStudents());
			
			//save the students
			System.out.println("\nSaving students ...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved students: "+tempCourse.getStudents());
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!!");
			
		} finally {
			session.close();
			factory.close();
		}
	}
}
