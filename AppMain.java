package com.example.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.demo.config.HibernateConfig;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

public class AppMain {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        var service = context.getBean(EmployeeService.class);

        Employee emp = new Employee();
        emp.setName("John Doe");
        emp.setDepartment("IT");
        emp.setSalary(60000);

        service.save(emp);
        System.out.println("Saved Employee: " + emp.getName());

        emp.setSalary(65000);
        service.update(emp);
        System.out.println("Updated Employee Salary: " + emp.getSalary());

        service.list().forEach(e -> System.out.println("Employee: " + e.getName()));

        service.delete(emp.getId());
        System.out.println("Deleted Employee with ID: " + emp.getId());

        context.close();
    }
}

