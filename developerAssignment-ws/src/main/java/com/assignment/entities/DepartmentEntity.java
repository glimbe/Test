package com.assignment.entities;

import java.io.Serializable;

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
@Entity(name="department")
public class DepartmentEntity implements Serializable
{

	private static final long serialVersionUID = -6290651962255412206L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;

	public DepartmentEntity()
	{
		
	}
	
	public DepartmentEntity(String name)
	{
		this.name = name;
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

	@Override
	public String toString()
	{
		String str = " name: " + this.name + " id: " + this.id ;
	    return str;			
	}
}
