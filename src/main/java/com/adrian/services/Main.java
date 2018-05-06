package com.adrian.services;

import java.util.Random;
import java.util.stream.IntStream;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import com.adrian.producers.LoggerProducer;
import com.adrian.producers.SortServiceProducer;
import com.adrian.services.responses.SortResponse;
import com.adrian.utils.AlgorithmsUtil;

public class Main {

	private static final int ARRAY_SIZE = 10000;
	private static final int LOWER_BOUND = 0;
	private static final int UPPER_BOUND = 100000;

	public static void main(String[] args) {
		var initializer = SeContainerInitializer.newInstance();

		try (SeContainer container = initializer.disableDiscovery()
				.addBeanClasses(GreetingService.class, LoggerProducer.class, SelectionSortService.class,
						InsertionSortService.class, ShellSortService.class, SortServiceProducer.class,
						MergeSortService.class, ImprovedMergeSortService.class, QuickSortService.class,
						ImprovedQuickSortService.class)
				.initialize()) {

			var service = container.select(GreetingService.class).get();

			@SuppressWarnings("unchecked")
			SortService<Integer> sortService = (SortService<Integer>) container.select(SortService.class).get();

			// var array = new Integer[] { 9, 2, 1, 6, 4, 7, 0, 8, 3, 5 };

			Random random = new Random();
			var unboxedArray = random.ints(ARRAY_SIZE, LOWER_BOUND, UPPER_BOUND).toArray();

			var array = IntStream.of(unboxedArray).boxed().toArray(Integer[]::new);

			var startTime = System.currentTimeMillis();

			SortResponse<Integer> response = sortService.sort(array, (x, y) -> x < y);

			var duration = System.currentTimeMillis() - startTime;

			// System.out.println(Arrays.toString(array));
			System.out.println(String.format("DURATION: %d", duration));

			var isValidSort = AlgorithmsUtil.validateSort(array, (x, y) -> x < y);

			// System.out.println(response);
			System.out.println(isValidSort);

			service.greet();
		}
	}
}