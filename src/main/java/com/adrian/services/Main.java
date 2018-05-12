package com.adrian.services;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import com.adrian.producers.LoggerProducer;
import com.adrian.producers.SortServiceProducer;

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
						ImprovedQuickSortService.class, SortIntegersReportService.class)
				.initialize()) {

			var sortReportService = container.select(SortIntegersReportService.class).get();
			var report = sortReportService.getReport(ARRAY_SIZE, LOWER_BOUND, UPPER_BOUND, (x, y) -> x < y);

			System.out.println(report);
		}
	}
}