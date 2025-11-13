package com.example.demo.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import com.example.demo.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Employee employee) {
        getSession().persist(employee);
    }

    @Override
    public void update(Employee employee) {
        getSession().merge(employee);
    }

    @Override
    public void delete(Long id) {
        Employee emp = getSession().get(Employee.class, id);
        if (emp != null) getSession().remove(emp);
    }

    @Override
    public Employee get(Long id) {
        return getSession().get(Employee.class, id);
    }

    @Override
    public List<Employee> list() {
        return getSession().createQuery("from Employee", Employee.class).list();
    }
}
