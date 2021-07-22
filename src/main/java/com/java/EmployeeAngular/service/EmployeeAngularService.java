package com.java.EmployeeAngular.service;

import java.util.List;
import java.util.Optional;

import com.java.EmployeeAngular.model.Employee;

public interface EmployeeAngularService {

    List<Employee> findAll();

    Optional<Employee> findById(Integer id);

    void create(Employee emp);

	void update(Employee emp);

	void deleteById(Integer id);

	List<Employee> findByFirstName(String firstName);

	List<Employee> findByLastName(String lastName);

	List<Employee> findByCity(String city);

	List<Employee> sortByJoiningDate();

	List<Employee> sortByCity();
}

