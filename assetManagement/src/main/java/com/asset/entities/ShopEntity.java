package com.asset.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.asset.model.ShopAddress;

@Entity
@Table(name="shop")
public class ShopEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1621516265614109183L;
	
	@Id
	@Column(name = "name")
	private String name;
	
	/*@OneToOne(mappedBy = "shop")
    private ShopAddress address;*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public ShopAddress getAddress() {
		return address;
	}

	public void setAddress(ShopAddress address) {
		this.address = address;
	}*/
	
	
}
