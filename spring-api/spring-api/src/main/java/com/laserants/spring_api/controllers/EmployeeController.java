package com.laserants.spring_api.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laserants.spring_api.models.EmployeeModel;
import com.laserants.spring_api.services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public ArrayList<EmployeeModel> getEmployees(){
		return this.employeeService.getEmployees();
	}
	
	@PostMapping
	public ResponseEntity<EmployeeModel> saveEmployee(@RequestBody EmployeeModel employee) {
		return this.employeeService.saveEmployee(employee);
	}
	
	@GetMapping(path = "/{id}")
	public EmployeeModel getEmployeeById(@PathVariable("id") Integer id){
		return this.employeeService.getByid(id);
	}
	
	@PutMapping(path = "/{id}")
	public EmployeeModel updateEmployeeById(@RequestBody EmployeeModel employee, @PathVariable("id") Integer id) {
		return this.employeeService.updateById(employee, id);
	}
	
	@DeleteMapping(path = "/{id}")
	public Boolean deleteEmployeeById(@PathVariable("id") Integer id) {
		return this.employeeService.deleteEmployee(id);
	}
	
}
