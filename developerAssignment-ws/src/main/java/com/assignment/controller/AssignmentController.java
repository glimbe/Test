package com.assignment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.models.Department;
import com.assignment.models.Employee;
import com.assignment.service.AssignmentService;

/**
 * Controller Class for CRUD operations on {Employee} Data.
 * @author Gokul Limbe
 *
 */
@RestController
@RequestMapping("/assignment")
public class AssignmentController
{
	private static Logger logger = LoggerFactory.getLogger(AssignmentController.class);
	
	@Autowired
	private AssignmentService assignmentService;
	
	/**
	 * GET method to get the list of Employees saved into table. 
	 * @return List<Employee> employees - return list of employees.
	 */
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getEmployees()
	{
		logger.info("Fetching and returning list of employees");
		
		List<Employee> employees = assignmentService.getEmployee();
		
		logger.info("Employee list from service: " + employees.toString());
		
		return new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
	}
	
	/**
	 * POST method to save employee data into table.
	 * @param emp - data needs to be save.
	 * @return - updated employee object.
	 */
	@RequestMapping(value ="/employees", method = RequestMethod.POST)
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee emp) 
	{
		logger.info("Saving employee data:" + emp.toString());
		
		emp = assignmentService.saveEmployee(emp);
		
		return new ResponseEntity<Employee>(emp,HttpStatus.CREATED);
	}
	
	/**
	 * PUT method to update Employee record.
	 * @param empId - id of which records needs to be updated.
	 * @param emp - data that get update.
	 * @return - updated data.
	 */
	@RequestMapping(value ="/employees/{empId}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("empId") Integer empId, @RequestBody Employee emp)
	{
		logger.info("Updating employee data: " + emp.toString()+ "for: " + empId);
		
		Employee response = assignmentService.updateEmployee(empId, emp);
		
		return new ResponseEntity<Employee>(response,HttpStatus.OK);
	}
	
	/**
	 * DELETE method to delete a particular record of given id.
	 * @param empId - id of which records get deleted.
	 * @return message - Message for successfully deleted.
	 */
	@RequestMapping(value ="/employees/{empId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployee(@PathVariable("empId")Integer empId)
	{
		logger.info("Deleting employee record for: " + empId);
		
		assignmentService.deleteEmployee(empId);
		
		return new ResponseEntity<String>("Record get deleted succussfully",HttpStatus.OK);
	}
	
	/**
	 * POST method to save department data into table.
	 * @param emp - data needs to be save.
	 * @return - updated department object.
	 */
	@RequestMapping(value ="/departments", method = RequestMethod.POST)
	public ResponseEntity<Department> saveDepartment(@RequestBody Department dept) 
	{
		logger.info("Saving employee data:" + dept.toString());
		
		dept = assignmentService.saveDepartment(dept);
		
		return new ResponseEntity<Department>(dept,HttpStatus.CREATED);
	}
}
