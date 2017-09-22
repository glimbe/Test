package com.assignment.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.entities.DepartmentEntity;
import com.assignment.entities.EmployeeEntity;
import com.assignment.models.Department;
import com.assignment.models.Employee;
import com.assignment.repositories.DepartmentRepository;
import com.assignment.repositories.EmployeeRepository;

@Service
public class AssignmentService
{
	private static final Logger logger = LoggerFactory.getLogger(AssignmentService.class);
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private DepartmentRepository deptRepo;
	
	
	/**
	 * This method will save the Employee data into table. 
	 * @param emp - Employee object with data.
	 * @return Employee - updated object of employee.
	 */
	@Transactional(rollbackFor = Exception.class)
	public Employee saveEmployee(Employee emp) 
	{
		logger.info("Start executing saveEmployee method");
		
		EmployeeEntity entity = new EmployeeEntity(emp.getName(), emp.getDepartId());

		entity = empRepo.save(entity);

		if (null != entity.getId())
		{
			emp.setId(entity.getId());
		} else
		{
			logger.info("Issue while save employee data");
		}

		logger.info("Employee data get saved successfully");
		
		return emp;
	}

	/**
	 * This method will return the list of Employees.
	 * @return list - employee list.
	 */
	public List<Employee> getEmployee()
	{
		logger.info("Start executing getEmployee method");
		
		List<Employee> employees = new ArrayList<>();
		
		List<EmployeeEntity> empEntities = (List<EmployeeEntity>) empRepo.findAll();
		
		for (EmployeeEntity employeeEntity : empEntities)
		{
			Employee emp = new Employee(employeeEntity.getId(),employeeEntity.getName(),employeeEntity.getDepartId());
			employees.add(emp);
		}
		
		logger.info("List of employees fetch from db: " + employees.toString());
		
		return employees;
	}
	
	/**
	 * This method will update the record of given employee id.
	 * @param empId - id of which data needs to be updated.
	 * @param emp - Employee object with new data.
	 * @return - updated entity.
	 */
	@Transactional(rollbackFor = Exception.class)
	public Employee updateEmployee(Integer empId,Employee emp)
	{
		logger.info("Start executing updateEmployee method");
		
		EmployeeEntity empEntity = empRepo.findOne(empId);
		
		logger.info("Existing data for passed id :" + empId + "data: " + empEntity.toString());
		
		empEntity.setName(emp.getName());
		
		empEntity.setDepartId(emp.getDepartId());
		
		empRepo.save(empEntity);
		
		emp.setId(empId);
		
		logger.info("Data get updated successfully");
		
		return emp;
		
	}
	
	/**
	 * This method will delete the record of given Id.
	 * @param empId - Id of which record needs to be deleted.
	 */
	public void deleteEmployee(Integer empId)
	{
		logger.info("Start executing deleteEmployee method");
		
		empRepo.delete(empId);
		
		logger.info("Record get deleted successfully");
	}
	
	/**
	 * This method will save the department data into table.
	 * @param department - data that needs to be saved.
	 * @return - updated department.
	 */
	@Transactional(rollbackFor = Exception.class)
	public Department saveDepartment(Department department)
	{
		logger.info("Start executing saveDepartment method");
		
		DepartmentEntity departEntity = new DepartmentEntity(department.getName());
		
		departEntity = deptRepo.save(departEntity);
		
		if (null != departEntity.getId())
		{
			department.setId(departEntity.getId());
		} else
		{
			logger.info("Issue while save department data");
		}

		logger.info("Department data get saved successfully");
		
		return department;
	}
}
