package com.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.entities.EmployeeEntity;

/**
 * Repository class for Employee Database operation.
 * @author Gokul Limbe
 *
 */
@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer>
{

}
