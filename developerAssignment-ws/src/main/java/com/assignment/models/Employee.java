package com.assignment.models;

/**
 * Model class for Employee table.
 * @author Gokul Limbe
 *
 */
public class Employee 
{

	private Integer id;
	
	private String name;
	
	private Integer departId;
	
	public Employee()
	{
		
	}

	public Employee(Integer id,String name, Integer departId)
	{
		this.id = id;
		this.name = name;
		this.departId = departId;
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
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}
	
	@Override
	public String toString()
	{
		String str = " name: " + this.name + " id: " + this.id + " departId: " +this.departId ;
	    return str;			
	}
}
