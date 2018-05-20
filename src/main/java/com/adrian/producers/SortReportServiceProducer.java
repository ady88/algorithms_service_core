package com.adrian.producers;

import javax.enterprise.inject.Produces;

import com.adrian.qualifiers.IntegerSortReport;
import com.adrian.services.SortReportService;

public class SortReportServiceProducer {
	@SuppressWarnings("rawtypes")
	@Produces
	public SortReportService getSortReportService(@IntegerSortReport SortReportService<?> sortIntegerReport) {
		SortReportService<?> result;

		result = sortIntegerReport;
		return result;
	}
}
