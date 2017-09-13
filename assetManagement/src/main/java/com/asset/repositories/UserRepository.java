package com.asset.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asset.entities.User;

public interface UserRepository extends JpaRepository<User, Long>
{
	User findOneByUsername(String userName);
}
