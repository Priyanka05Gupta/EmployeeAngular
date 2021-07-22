package com.java.EmployeeAngular.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java.EmployeeAngular.model.Employee;

@Repository
public interface EmployeeAngularDao extends CrudRepository<Employee, Integer> {

	/*
	 * @Query("update employee e set e.city=:city where e.id=:id") void update(int
	 * id, String city);
	 */

    /*@Query()
    Employee update(Employee emp);*/
	
	@Query("from Employee where soft_delete=0")
	List<Employee> findAll();

	@Query("from Employee where id=:id and soft_delete=0")
	Optional<Employee> findById(Integer id);

	@Query("from Employee where first_name=:first_name and soft_delete=0")
	List<Employee> findByFirstName(String first_name);

	@Query("from Employee where last_name=:last_name and soft_delete=0")
	List<Employee> findByLastName(String last_name);

	@Query("from Employee where city=:city and soft_delete=0")
	List<Employee> findByCity(String city);

	@Query("from Employee order by joining_date asc")
	List<Employee> sortByJoiningDate();

	@Query("from Employee order by city asc")
	List<Employee> sortByCity();
}

