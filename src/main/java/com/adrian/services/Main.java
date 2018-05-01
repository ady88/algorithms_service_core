package com.adrian.services;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import com.adrian.producers.LoggerProducer;
import com.adrian.producers.SortServiceProducer;
import com.adrian.services.responses.SortResponse;
import com.adrian.utils.AlgorithmsUtil;

public class Main {

	public static void main(String[] args) {
		var initializer = SeContainerInitializer.newInstance();

		try (SeContainer container = initializer
				.disableDiscovery().addBeanClasses(GreetingService.class, LoggerProducer.class,
						SelectionSortService.class, InsertionSortService.class, SortServiceProducer.class)
				.initialize()) {

			var service = container.select(GreetingService.class).get();

			@SuppressWarnings("unchecked")
			SortService<Integer> sortService = (SortService<Integer>) container.select(SortService.class).get();

			var array = new Integer[] { 9, 2, 1, 6, 4, 7, 0 };

			SortResponse<Integer> response = sortService.sort(array, (x, y) -> y > x);

			var isValidSort = AlgorithmsUtil.validateSort(array, (x, y) -> y > x);

			System.out.println(response);
			System.out.println(isValidSort);

			service.greet();
		}
	}
}