package com.java.EmployeeAngular.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.java.EmployeeAngular.model.Employee;
import com.java.EmployeeAngular.service.EmployeeAngularService;

//reference for datatables https://www.youtube.com/watch?v=mhS0eMGUliQ
@RestController
@RequestMapping(value="/employees", produces= {"application/json"})
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeAngularController {

    private EmployeeAngularService empService;

    public EmployeeAngularController(EmployeeAngularService empService) {
        this.empService = empService;
    }

    @GetMapping(value="/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }
    
    @GetMapping(value="/empDataTable")
    public ModelAndView empDataTable(){
        return new ModelAndView("EmployeeDataTable");
    }
    
    @GetMapping(value="/register")
    public ModelAndView register(Model model){
		
		Employee emp = new Employee(); 
		List<String> cityList = Arrays.asList("Pune","Mumbai"); 
		List<String> genderList = Arrays.asList("M", "F");
		  
		model.addAttribute("emp", emp); 
		model.addAttribute("city", cityList);
		model.addAttribute("gender", genderList);
		 
    	System.out.println("register form");
        return new ModelAndView("register_form");
    }

    @GetMapping(value="/findAll")
    public ResponseEntity<Iterable<Employee>> findAll() {
	   System.out.println("inside FindAll() method");
	   try {
	       return new ResponseEntity<Iterable<Employee>>(empService.findAll(), HttpStatus.OK);
	   }catch(Exception e) {
		   return new ResponseEntity<Iterable<Employee>>(HttpStatus.BAD_REQUEST);
	   }
    }
   
   @GetMapping(value="/findByIdForUpdate")
   public ModelAndView findByIdForUpdate(@RequestParam("id") Integer id, Model model) {
       System.out.println("find"+id);
       Optional<Employee> emp = empService.findById(id);
    	List<String> cityList = Arrays.asList("Pune","Mumbai"); 
		List<String> genderList = Arrays.asList("M", "F");
		
		model.addAttribute("emp", emp);
		model.addAttribute("first_name", emp.get().getFirst_name());
		model.addAttribute("last_name", emp.get().getLast_name());
		model.addAttribute("joining_date", emp.get().getJoining_date());
		model.addAttribute("salary", emp.get().getSalary());
		model.addAttribute("city", cityList); 
	    model.addAttribute("gender",genderList);
	    System.out.println("update form");
       return new ModelAndView("EmployeeUpdate");
  }

    @GetMapping(value="/findById")
    public ResponseEntity<Optional<Employee>> findById(@RequestParam("id") Integer id) {
        System.out.println("find"+id);
        Optional<Employee> emp = empService.findById(id);
        if(emp!=null) {
            return new ResponseEntity<Optional<Employee>>(emp, HttpStatus.OK);
        }else {
            return new ResponseEntity<Optional<Employee>>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(value="/findByFirstName")
    public ResponseEntity<List<Employee>> findByFirstName(@RequestParam("firstName") String firstName) {
        System.out.println("find by firstName "+firstName);
        List<Employee> emp = empService.findByFirstName(firstName);
        if(emp!=null) {
            return new ResponseEntity<List<Employee>>(emp, HttpStatus.OK);
        }else {
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/findByLastName")
    public ResponseEntity<List<Employee>> findByLastName(@RequestParam("lastName") String lastName) {
        System.out.println("find by lastName "+lastName);
        List<Employee> emp = empService.findByLastName(lastName);
        if(emp!=null) {
            return new ResponseEntity<List<Employee>>(emp, HttpStatus.OK);
        }else {
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(value="/findByCity")
    public ResponseEntity<List<Employee>> findByCity(@RequestParam("city") String city) {
        System.out.println("find by city "+city);
        List<Employee> emp = empService.findByCity(city);
        if(emp!=null) {
            return new ResponseEntity<List<Employee>>(emp, HttpStatus.OK);
        }else {
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value="/save")
    public ResponseEntity<String> create(Employee emp) throws Exception {
        if(!empService.findById(emp.getId()).isPresent()){
            empService.create(emp);
            return new ResponseEntity<String>("Created new Employee!", HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Conflict! Employee already exists!", HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value="/update")
    public ResponseEntity<String> update(@ModelAttribute Employee emp) {
	    System.out.println("update method: "+emp.getId());
	    Optional<Employee> existingEmp = empService.findById(emp.getId());
        if(existingEmp.isPresent()) {
        	empService.update(emp);
            return new ResponseEntity<String>("Employee updated Successfully!", HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Conflict! Employee Not found", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteById(@RequestParam("id") Integer id, @RequestParam("isSoftDelete") boolean isSoftDelete){
    	System.out.println("isSoftDelete: "+isSoftDelete);
	    Optional<Employee> existingEmp = empService.findById(id);
        if(existingEmp.isPresent()) {
        	if(!isSoftDelete) {        		
        		empService.deleteById(id);
                return new ResponseEntity<String>("Successfully Hard deleted the employee", HttpStatus.OK);
        	}else {
            	System.out.println("isSoftDelete: "+isSoftDelete);
        		Employee emp = existingEmp.get();
        		emp.setSoftDelete(isSoftDelete);
        		empService.update(emp);
        		return new ResponseEntity<String>("Successfully Soft deleted the employee", HttpStatus.OK);
        	}
        }else {
            return new ResponseEntity<String>("Conflict, employee does not exists", HttpStatus.CONFLICT);
        }
    }
    
    @GetMapping(value="/sortByJoiningDate")
    public ResponseEntity<List<Employee>> sortByJoiningDate() {
        System.out.println("sortByJoiningDate ");
        List<Employee> emp = empService.sortByJoiningDate();
        if(emp!=null) {
            return new ResponseEntity<List<Employee>>(emp, HttpStatus.OK);
        }else {
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(value="/sortByCity")
    public ResponseEntity<List<Employee>> sortByCity() {
        System.out.println("sortByCity ");
        List<Employee> emp = empService.sortByCity();
        if(emp!=null) {
            return new ResponseEntity<List<Employee>>(emp, HttpStatus.OK);
        }else {
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
    }


}

