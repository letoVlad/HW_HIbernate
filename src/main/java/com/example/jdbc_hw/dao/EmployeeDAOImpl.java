package com.example.jdbc_hw.dao;


import com.example.jdbc_hw.config.HibernateSessionFactoryUtil;
import com.example.jdbc_hw.model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDAOImpl implements EmployeeDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Создание(добавление) сущности Employee в таблицу
    @Override
    public Employee addEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
        return employee;
    }

    //Получение конкретного объекта Employee по id
    public Employee findById(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        }
    }

    //Получение списка всех объектов Employee из базы
    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = (List<Employee>) HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("From Employee").list();
        return employeeList;
    }

    //Изменение конкретного объекта Employee в базе по id
    @Override
    public Employee editEmployee(Integer id, Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Employee editEmployee = session.get(Employee.class, id);
            if (editEmployee != null) {
                session.getTransaction().begin();
                editEmployee.setAge(employee.getAge());
                editEmployee.setFirstName(employee.getFirstName());
                editEmployee.setLastName(employee.getLastName());
                editEmployee.setGender(employee.getGender());
                session.getTransaction().commit();
            }
            return editEmployee;
        }
    }

    //Удаление конкретного объекта Employee из базы по id
    @Override
    public Boolean deleteId(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Employee deleteEmploye = session.get(Employee.class, id);
            if (deleteEmploye != null) {
                session.getTransaction().begin();
                session.delete(deleteEmploye);
                session.getTransaction().commit();
                return true;
            }
        }
        return false;
    }
}