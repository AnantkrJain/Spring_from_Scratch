package com.imarticus.demo.SpringAnnotationProject;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 *
 */
//@Configuration
@ComponentScan
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
       // AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(Config.class);
        ctx.refresh();
       
        Employee emp = ctx.getBean(Employee.class);
        emp.works();
        System.out.println(emp.toString());
    }
}

