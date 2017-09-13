package com.asset.advice;


import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.h2.jdbc.JdbcSQLException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerAdvice
{

	@ExceptionHandler(SQLException.class)
	@ResponseBody
	public ResponseEntity<String> sqlException(SQLException ex)
	{
		System.out.println("in exception handler advice");
		String s = "sql exception occured";
		return new ResponseEntity<String>(s, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
