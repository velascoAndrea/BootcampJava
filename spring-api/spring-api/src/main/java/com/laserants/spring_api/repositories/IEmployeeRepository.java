package com.laserants.spring_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laserants.spring_api.models.EmployeeModel;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeModel, Integer>{
// Se le pasa Modelo y el tipo de dato de la llave primaria
}
