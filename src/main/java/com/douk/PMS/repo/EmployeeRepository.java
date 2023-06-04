package com.douk.PMS.repo;

import com.douk.PMS.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //@Query("SELECT e FROM employee e WHERE CONCAT(e.first_name, ' ', e.last_name) LIKE '%?1%'")
    List<Employee> findByFirstNameContaining(String name);

    List<Employee> findByLastNameContaining(String name);
}
