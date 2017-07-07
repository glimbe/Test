package com.asset.model;

/**
 * Model class for Shop with Address
 * This model will be used to get and send shop's data.
 */
public class Shop
{
    private String name;
  
    private ShopAddress address;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ShopAddress getAddress() {
		return address;
	}
	
	public void setAddress(ShopAddress address) {
		this.address = address;
	}
  
  
}
