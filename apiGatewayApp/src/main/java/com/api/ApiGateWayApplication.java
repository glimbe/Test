package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableResourceServer
public class ApiGateWayApplication 
{
	 public static void main(String[] args) 
	 {
	        SpringApplication.run(ApiGateWayApplication.class, args);
	  }
	 
}
