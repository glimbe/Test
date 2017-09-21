package com.assignment.models;

/**
 * Model class for Department table.
 * @author Gokul Limbe
 *
 */
public class Department
{
	private Integer id;
	
	private String name;

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
	
	@Override
	public String toString()
	{
		String str = " name: " + this.name + " id: " + this.id ;
	    return str;			
	}
}
