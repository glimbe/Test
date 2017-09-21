package com.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.entities.DepartmentEntity;

/**
 * Repository class for Department Database operation.
 * @author Gokul Limbe
 *
 */
@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Integer> {

}
