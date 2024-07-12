package com.laserants.spring_api.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.laserants.spring_api.models.EmployeeModel;
import com.laserants.spring_api.repositories.IEmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	IEmployeeRepository employeeRepository;
	
	public ArrayList<EmployeeModel> getEmployees(){
		return (ArrayList<EmployeeModel>) employeeRepository.findAll();
	}
	
	public ResponseEntity<EmployeeModel> saveEmployee(EmployeeModel employee) {
		// validaciones extra o logica va aca
		//return employeeRepository.save(employee);
		
		return new ResponseEntity<EmployeeModel>(employeeRepository.save(employee), HttpStatus.CREATED);		
	}
	
	public EmployeeModel getByid(Integer id){
		EmployeeModel employee = employeeRepository.findById(id).orElse(null);
		
		if (employee == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "the employee was not found");
		}
		
		return employee;
	}
	
	public EmployeeModel updateById(EmployeeModel request, Integer id) {
		EmployeeModel employee = employeeRepository.findById(id).get();
		
		employee.setName(request.getName());
		employee.setEmail(request.getEmail());
		
		return employeeRepository.save(employee);
	}
	
	public Boolean deleteEmployee(Integer id) {
		try {
			employeeRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
