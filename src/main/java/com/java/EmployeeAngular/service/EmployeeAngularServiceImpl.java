package com.java.EmployeeAngular.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.EmployeeAngular.model.Employee;
import com.java.EmployeeAngular.repository.EmployeeAngularDao;

@Service
public class EmployeeAngularServiceImpl implements EmployeeAngularService{

	@Autowired
    private EmployeeAngularDao empDao;

    @Override
    public List<Employee> findAll() {
       // System.out.println("inside findAll method");
        return (List<Employee>) empDao.findAll();
        // return null;
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return empDao.findById(id);
         //return null;
    }

    @Override
    public void create(Employee emp) {
       empDao.save(emp);
    }

	
	  @Override 
	  public void update(Employee emp) { 
		  empDao.save(emp);
	  }
	 

	@Override
	public void deleteById(Integer id) {
		empDao.deleteById(id);
	}

	@Override
	public List<Employee> findByFirstName(String firstName) {
		return empDao.findByFirstName(firstName);
	}

	@Override
	public List<Employee> findByLastName(String lastName) {
		return empDao.findByLastName(lastName);
	}

	@Override
	public List<Employee> findByCity(String city) {
		return empDao.findByCity(city);
	}

	@Override
	public List<Employee> sortByJoiningDate() {
		return empDao.sortByJoiningDate();
	}

	@Override
	public List<Employee> sortByCity() {
		return empDao.sortByCity();
	}


}

