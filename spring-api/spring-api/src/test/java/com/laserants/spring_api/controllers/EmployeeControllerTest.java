package com.laserants.spring_api.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getAllUsersSucess() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/employee")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers
				.content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		
	}
	
	@Test
	public void saveNewUserSuccess() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.post("/employee")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n"
						+ "    \"name\": \"Josue Calderon\",\n"
						+ "    \"email\": \"josue@laserants.com\"\n"
						+ "}"))
		.andExpect(status().isCreated())
		.andExpect(MockMvcResultMatchers
				.content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		
	}
	
}
