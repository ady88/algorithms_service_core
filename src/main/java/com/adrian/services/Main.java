package com.adrian.services;

import java.lang.annotation.Annotation;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import com.adrian.producers.LoggerProducer;
import com.adrian.producers.SortServiceProducer;

public class Main {

	private static final int ARRAY_SIZE = 10000;

	public static void main(String[] args) {
		var initializer = SeContainerInitializer.newInstance();

		try (SeContainer container = initializer.disableDiscovery()
				.addBeanClasses(GreetingService.class, LoggerProducer.class, SelectionSortService.class,
						InsertionSortService.class, ShellSortService.class, SortServiceProducer.class,
						MergeSortService.class, ImprovedMergeSortService.class, QuickSortService.class,
						ImprovedQuickSortService.class, SortIntegerReportService.class)
				.initialize()) {
			Annotation sorIntegerReportServiceAnnotation = SortIntegerReportService.class.getAnnotations()[0];
			SortReportService<Integer> sortReportService = (SortIntegerReportService) container
					.select(sorIntegerReportServiceAnnotation).get();
			var report = sortReportService.getReport(ARRAY_SIZE, (x, y) -> x < y);

			System.out.println(report);
		}
	}
}