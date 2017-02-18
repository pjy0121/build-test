package com.aulos.buildtest.service;

import org.springframework.stereotype.Service;

@Service
public class DummyService {

	public boolean checkPrimeNumber(int value) {
		
		if (value == 2) {
			
			return true;
		}
		
		if (value % 2 == 0 || value < 2) {
			
			return false;
		}
		
		for (long i = 3; i < value; i++) {
			
			if (value % i == 0) {
				
				return false;
			}
		}
		
		return true;
	}
}
