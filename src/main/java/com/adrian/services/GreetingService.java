package com.adrian.services;


import java.util.logging.Logger;

import javax.inject.Inject;


public class GreetingService {
	
	@Inject
	private Logger log;
	
	public void greet() {
		log.info("This is a logger message.");
		System.out.println("Hello, world.");
	}
}