package com.asset.controller;

import java.io.IOException;
import java.net.MalformedURLException;
//import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asset.model.Employee;
import com.asset.model.ErrorObject;
import com.asset.model.Location;
import com.asset.model.Shop;
import com.asset.service.AssestService;
import com.asset.validation.AssestDataValidator;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

@RestController
@EnableResourceServer
public class AssestManagementController
{
	@Autowired
	private AssestService assetService;
	
	
	private static Logger logger = LoggerFactory.getLogger(AssestManagementController.class);
	
	
	@RequestMapping(value = "/ghostbusters")
    public List<String> available() {
        List<String> movies = new ArrayList<>();
        movies.add("Ghostbusters (1984)");
        movies.add("Ghostbusters (2016)");
        return movies;
    }
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getEmployees(Authentication authentication)
	{
		String name = authentication.getName();
		System.out.println("Name: "+name);
		return new ResponseEntity<List<String>>(assetService.getEmployee(),HttpStatus.OK);
	}
	@RequestMapping(value ="/employees", method = RequestMethod.POST)
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee emp) throws SQLException
	{
		
		assetService.saveEmployee(emp);
		
		return new ResponseEntity<Employee>(emp,HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="/shops", method = RequestMethod.POST)
	public ResponseEntity saveShop(@RequestBody Shop shop) throws JsonSyntaxException, JsonIOException, ClassNotFoundException, MalformedURLException, SQLException, IOException
	{
		logger.info("Start executing saveShop Method");
		
		if(GenericValidator.isBlankOrNull(shop.getName()))
		{
			ErrorObject errorObject = new ErrorObject();
			errorObject.setField("ShopName");
			errorObject.setMessage("Shop Name can't be blank, please enter valid value");
			
			return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
		}
		shop = assetService.saveShop(shop);
		
		logger.info("Response of saveShop: " + shop.toString());
		
		return new ResponseEntity<Shop>(shop, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/shops", method = RequestMethod.GET)
	public ResponseEntity getNearestShop(@RequestParam String latitude,@RequestParam String longitude)
	{
		logger.info("Start executing getShop Method");
		
		if(GenericValidator.isBlankOrNull(latitude) || GenericValidator.isBlankOrNull(longitude))
		{
			ErrorObject errorObject = new ErrorObject();
			errorObject.setField("Latitude/Longitude");
			errorObject.setMessage("Latitude and Longitude can't be blank, please enter valid value");
			
			return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
		}
		Location location = assetService.getNearestShop(Double.parseDouble(latitude),Double.parseDouble(longitude));
		
		logger.info("Response of getShop: " + location.toString());
		
		return new ResponseEntity<Location>(location,HttpStatus.OK);
	}
}
