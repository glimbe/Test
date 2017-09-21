package com.assignment.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class for Department table.
 * @author Gokul Limbe
 *
 */
@Entity(name = "employee")
public class EmployeeEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="dept_id")
	private Integer deptId;
	
	public EmployeeEntity()
	{
		
	}
	
	public EmployeeEntity(String name,Integer deptId)
	{
		this.name = name;
		this.deptId = deptId;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDepartId() {
		return deptId;
	}

	public void setDepartId(Integer deptId) {
		this.deptId = deptId;
	}
	
	@Override
	public String toString()
	{
		String str = " name: " + this.name + " id: " + this.id + " departId: " +this.deptId ;
	    return str;			
	}
}
