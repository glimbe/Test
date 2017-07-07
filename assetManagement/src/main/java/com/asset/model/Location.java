package com.asset.model;

public class Location
{
	private double latitude;
	
	private double longitude;

	//Default Constructor
	public Location()
	{
		
	}
	//Parameterized constructor
	public Location(double lat, double lng)
	{
		this.latitude = lat;
		this.longitude = lng;
	}
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
