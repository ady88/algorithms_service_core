package com.adrian.services;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import com.adrian.services.GreetingService;

import com.adrian.producers.LoggerProducer;

public class Main {

	public static void main(String[] args) {
		var initializer = SeContainerInitializer.newInstance();

		try (SeContainer container = initializer.disableDiscovery()
				.addBeanClasses(GreetingService.class, LoggerProducer.class).initialize()) {
			GreetingService service = container.select(GreetingService.class).get();
			service.greet();
		}
	}
}