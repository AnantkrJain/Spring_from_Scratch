package com.imarticus.demo.SpringCoreProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        //Traditional Way : -
        
//        Employee emp = new Employee();
//        emp.works();
        
        //Using Bean :-
        ApplicationContext ctx = new ClassPathXmlApplicationContext("SpringConfig.xml");
        
        Employee emp =(Employee) ctx.getBean("employee");
        emp.works();
        System.out.println(emp.toString());
        
        Employee emp2 =(Employee) ctx.getBean("employee2", Employee.class);
        System.out.println(emp2.toString());
        
        Department dept = ctx.getBean("department", Department.class);
        System.out.println(dept.toString());
        
        Department dept2 = ctx.getBean("department2", Department.class);
        System.out.println(dept2.toString());
        
        Country country = ctx.getBean("country",Country.class);
        country.travel();
    }
}
 