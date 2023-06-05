package com.example.jdbc_hw.dao;

import com.example.jdbc_hw.config.HibernateSessionFactoryUtil;
import com.example.jdbc_hw.model.City;
import com.example.jdbc_hw.model.Employee;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class CityDAOImpl implements CityDAO {
    @Override
    public List<String> employeeList(Integer cityId) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            City city = session.get(City.class, cityId);
            if (city != null) {
                List<String> employeeNames = city.getEmployees()
                        .stream()
                        .map(Employee::getFirstName)
                        .collect(Collectors.toList());
                System.out.println(employeeNames);
                return employeeNames;
            } else {
                return Collections.emptyList();
            }
        }
    }

    @Override
    //Получение конкретного объекта City по id
    public City findByIdCity(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(City.class, id);
        }
    }
}
