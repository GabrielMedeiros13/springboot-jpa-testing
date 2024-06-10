package com.example.spring_boot_jpa_testing;

import com.example.spring_boot_jpa_testing.model.Employee;
import com.example.spring_boot_jpa_testing.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(value = false)
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    // Junit test para saveEmployee
    @Test
    @Order(1)
    public void saveEmployeeTest(){

        Employee employee = Employee.builder()
                .firstName("Gabriel")
                .lastName("Medeiros")
                .email("gabriel@gmail.com")
                .build();

        employeeRepository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getEmployeeTest(){

        Employee employee = employeeRepository.findById(1L).get();

        Assertions.assertThat(employee.getId()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    public void getListOfEmployeesTest(){

        List<Employee> employees = employeeRepository.findAll();

        Assertions.assertThat(employees.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest(){

        Employee employee = employeeRepository.findById(1L).get();

        employee.setEmail("medeiros@gmail.com");

        Employee employeeUpdated = employeeRepository.save(employee);

        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("medeiros@gmail.com");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest(){

        Employee employee = employeeRepository.findById(1L).get();

        employeeRepository.delete(employee);

        Employee employee1 = null;

        Optional<Employee> optionalEmployee = employeeRepository.findByEmail("medeiros@gmail.com");

        if(optionalEmployee.isPresent()){
            employee1 = optionalEmployee.get();
        }

        Assertions.assertThat(employee1).isNull();
    }


}
