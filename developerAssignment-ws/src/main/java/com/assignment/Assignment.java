package com.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.assignment")
@EnableAutoConfiguration
public class Assignment
{
	public static void main(String[] args)
	{
		SpringApplication.run(Assignment.class, args);
	}
}
