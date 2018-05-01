package com.adrian.producers;

import javax.enterprise.inject.Produces;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.InsertionSort;
import com.adrian.qualifiers.SelectionSort;
import com.adrian.services.SortService;

public class SortServiceProducer<T> {

	private static final SortAlgorithm SORT_ALGORITHM = SortAlgorithm.INSERTION_SORT;

	@SuppressWarnings("rawtypes")
	@Produces
	public SortService getSortService(@SelectionSort SortService<?> selectionSort,
			@InsertionSort SortService<?> insertionSort) {
		SortService<?> result;

		switch (SORT_ALGORITHM) {
		case INSERTION_SORT:
			result = insertionSort;
			break;
		case SELECTION_SORT:
		default:
			result = selectionSort;
		}

		return result;
	}
}
