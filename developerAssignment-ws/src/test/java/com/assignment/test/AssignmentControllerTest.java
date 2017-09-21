package com.assignment.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.assignment.controller.AssignmentController;
import com.assignment.entities.EmployeeEntity;
import com.assignment.repositories.EmployeeRepository;
import com.assignment.service.AssignmentService;
import com.google.gson.Gson;

import ch.qos.logback.core.status.Status;

import static org.hamcrest.Matchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MockBeanConfig.class})
public class AssignmentControllerTest
{
	private MockMvc mockMvc;
	
	@Autowired
	private AssignmentService assignmentServiceMock;
	
	@Autowired
	private AssignmentController assignmentController;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Test
	public void testAssignmentService() throws Exception
	{
		EmployeeEntity entity = new EmployeeEntity("Gokul", 1);
		entity.setId(100);
		List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
		list.add(entity);
		mockMvc = MockMvcBuilders.standaloneSetup(assignmentController).build();
		
		Mockito.when(empRepo.findAll()).thenReturn(list);
		
		String result = mockMvc.perform(get("/assignment/employees/").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn().getResolvedException().getMessage();
		
		System.out.println("\n\n\n Response is "
	            + result);
	}
	
	@Test
	public void testUpdateEmployee() throws Exception
	{
		EmployeeEntity entity = new EmployeeEntity("Gokul", 1);
		
        mockMvc = MockMvcBuilders.standaloneSetup(assignmentController).build();
		
		Mockito.when(empRepo.save(entity)).thenReturn(entity);
		
		MvcResult result = mockMvc.perform(post("/assignment/employees/").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(entity))).andReturn();
		System.out.println("Response" + result.getResponse().getContentAsString());
		
		
	}
}
