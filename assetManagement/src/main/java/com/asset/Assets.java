package com.asset;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UserDetailsService;



@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.asset")
@EnableCaching
//@EnableDiscoveryClient
public class Assets
{

	public static void main(String[] args)
	{
		SpringApplication.run(Assets.class, args);
	}
	
	public CacheManager cacheManager()
	{
		EhCacheCacheManager ehCachemanager = null;
		try 
		{
			ehCachemanager = new EhCacheCacheManager(
					(net.sf.ehcache.CacheManager) cacheManagerFactory().getObject());
		} catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return ehCachemanager;
	}
	
	 @Bean
    public FactoryBean<net.sf.ehcache.CacheManager> cacheManagerFactory()
    {
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return factory;
    }
	 
	/*public UserDetailsService userDetailsService()
	{
		return new UserDetailsService();
	}*/

}
