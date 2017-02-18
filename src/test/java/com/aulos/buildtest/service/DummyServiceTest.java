package com.aulos.buildtest.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/context/**-context.xml")
public class DummyServiceTest {

	@Autowired
	private DummyService dummyService;

	List<Integer> allowed;
	List<Integer> disallowed;
	
	@Before
	public void setup() {
		
		allowed = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
		disallowed = new ArrayList<Integer>();
		
		for (int i = 1; i <= 100; i++) {
			
			if (!allowed.contains(i))
				disallowed.add(i);
		}
	}

	@Test
	public void shouldPassWithTwo() {

		int value = 2;

		boolean isPrimeNumber = dummyService.checkPrimeNumber(value);
		assertThat(isPrimeNumber, is(true));
	}
	
	@Test
	public void shouldPassWithOddNumbers() {

		for (int value : allowed) {
			
			boolean isPrimeNumber = dummyService.checkPrimeNumber(value);
			assertThat(isPrimeNumber, is(true));
		}
	}
	
	@Test
	public void shouldNotPassWithEvenNumber() {

		for (int value = 4; value <= 100; value += 2) {
			
			boolean isPrimeNumber = dummyService.checkPrimeNumber(value);
			assertThat(isPrimeNumber, is(false));
		}
	}

	@Test
	public void shouldNotPassWithOddNonPrimeNumber() {

		for (int value : disallowed) {
			
			boolean isPrimeNumber = dummyService.checkPrimeNumber(value);
			assertThat(isPrimeNumber, is(false));
		}
	}
	
	@Test
	public void shouldNotPassWithMinusValue() {

		int value = -1;
		
		boolean isPrimeNumber = dummyService.checkPrimeNumber(value);
		assertThat(isPrimeNumber, is(false));
	}
}
