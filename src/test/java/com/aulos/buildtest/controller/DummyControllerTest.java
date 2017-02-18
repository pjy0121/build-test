package com.aulos.buildtest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.aulos.buildtest.service.DummyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context/**-context.xml")
@WebAppConfiguration
public class DummyControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private DummyService dummyService;
	
	@InjectMocks
	private DummyController dummyControllerMock;
	
	
	List<Integer> allowed;
	List<Integer> disallowed;
	
	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(dummyControllerMock).build();
		
		allowed = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
		disallowed = new ArrayList<Integer>();
		
		for (int i = 1; i <= 100; i++) {
			
			if (!allowed.contains(i))
				disallowed.add(i);
		}
		
		for (int i : allowed)
			when(dummyService.checkPrimeNumber(i)).thenReturn(true);
		
		for (int i : disallowed) 
			when(dummyService.checkPrimeNumber(i)).thenReturn(false);
	}
	
	@Test
	public void shouldResponseWithCompositeNumber() throws Exception {
		
		int value = 2;
		
		mockMvc
			.perform(get("/").param("number", String.valueOf(value)))
			.andExpect(status().isOk())
			.andExpect(view().name("index"));
	}
	
	@Test
	public void shouldResponseWithPrimeNumber() throws Exception {
		
		int value = 4;
		
		mockMvc
			.perform(get("/").param("number", String.valueOf(value)))
			.andExpect(status().isOk())
			.andExpect(view().name("index"));
	}
		
	@Test
	public void shouldResponseWithErrorForMinusValue() throws Exception {
		
		int value = -1;
		
		mockMvc
			.perform(get("/").param("number", String.valueOf(value)))
			.andExpect(status().isOk())
			.andExpect(view().name("error"));
	}
	
	@Test
	public void shouldResponseWithErrorForInvalidParams() throws Exception {
		
		String value = "abcd";
		
		mockMvc
			.perform(get("/").param("number", value))
			.andExpect(status().isOk())
			.andExpect(view().name("error"));
	}
}
