package com.asset.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.asset.entities.ShopEntity;

@Component
public interface ShopRepository extends CrudRepository<ShopEntity, Integer>
{
	
}
