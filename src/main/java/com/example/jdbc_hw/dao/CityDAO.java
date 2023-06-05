package com.example.jdbc_hw.dao;

import com.example.jdbc_hw.model.City;
import com.example.jdbc_hw.model.Employee;

import java.util.List;

public interface CityDAO {
    List<String> employeeList(Integer id);
    
    //Получение конкретного объекта City по id
    City findByIdCity(Integer id);
}
