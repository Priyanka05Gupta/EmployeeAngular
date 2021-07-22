package com.java.EmployeeAngular.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="emp-list")
@XmlSeeAlso({Employee.class})
public class EmployeeList {

	private List<Employee> empList;
	private int size;

    public List<Employee> getEmpList() {
        return empList;
    }

    public void setEmpList(List<Employee> empList) {
        this.empList = empList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

