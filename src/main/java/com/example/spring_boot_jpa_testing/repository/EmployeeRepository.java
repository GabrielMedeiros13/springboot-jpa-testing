package com.example.spring_boot_jpa_testing.repository;

import com.example.spring_boot_jpa_testing.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
