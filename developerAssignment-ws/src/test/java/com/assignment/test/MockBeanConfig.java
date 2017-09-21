package com.assignment.test;

import javax.persistence.EntityManagerFactory;

import org.mockito.Mockito;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.assignment.repositories.DepartmentRepository;
import com.assignment.repositories.EmployeeRepository;



@ComponentScan(basePackages = { "com.assignment",
    "com.assignment.service" }, excludeFilters = @Filter(type = FilterType.ANNOTATION, value = {
        SpringBootApplication.class, Configuration.class, Bean.class }) )
@Configuration
@MockBean
public class MockBeanConfig
{

    /**
     * Mocking Error attributes.
     * 
     * @return mocked Error attributes object.
     */
    @Bean
    @Primary
    public ErrorAttributes registerErrorAttributesMock()
    {
        return Mockito.mock(ErrorAttributes.class);
    }

   
    @Bean
    @Primary
    public EmployeeRepository registerEmployeeRepositoryMock()
    {
        return Mockito.mock(EmployeeRepository.class);
    }
    
    @Bean
    @Primary
    public DepartmentRepository  registerDepartmentRepositoryMock()
    {
        return Mockito.mock(DepartmentRepository.class);
    }
    
    
    /**
     * This method is used to read properties from the .yml file.
     * 
     * @return Property Sources place holder configurer object.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties()
    {

        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(
            new ClassPathResource("application-test.yml"));

        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());

        return propertySourcesPlaceholderConfigurer;
    }

    /*@Bean
    public static WebMvcConfigurationSupport registerWebMvcConfigSupport()
    {
        final StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("exceptionHandler",
            ExceptionHandlerAdvice.class);

        final WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
        webMvcConfigurationSupport.setApplicationContext(applicationContext);

        return webMvcConfigurationSupport;
    }
*/
}