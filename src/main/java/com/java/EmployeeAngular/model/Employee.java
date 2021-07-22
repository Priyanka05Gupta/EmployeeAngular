package com.java.EmployeeAngular.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@XmlRootElement
@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date joining_date;

    @Column
    private int salary;

    @Column
    private String gender;

    @Column
    private String city;
    
    @JsonIgnore
    @Column(name="soft_delete")
    private boolean softDelete;

    public Employee(int id, String first_name, String last_name, Date joining_date, int salary, String gender, String city) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.joining_date = joining_date;
        this.salary = salary;
        this.gender = gender;
        this.city = city;
    }

    public Employee() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(Date joining_date) {
        this.joining_date = joining_date;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

	public boolean isSoftDelete() {
		return softDelete;
	}

	public void setSoftDelete(boolean softDelete) {
		this.softDelete = softDelete;
	}
  
}
