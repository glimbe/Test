package com.asset.validation;

import org.apache.commons.validator.GenericValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.asset.model.Shop;

@Component
public class AssestDataValidator
{

	private static Logger logger = LoggerFactory.getLogger(AssestDataValidator.class);
	
	public Errors validateShop(Shop shop, Errors error)
	{
		if(GenericValidator.isBlankOrNull(shop.getName()))
		{
			logger.info("Shop Name is blank");
			
			error.reject("ShopName","Shop Name can't blank");
		}
		return error;
	}
}
