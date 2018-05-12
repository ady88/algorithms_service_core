package com.adrian.services;

import java.util.function.BiPredicate;

import com.adrian.constants.StatusConstants;
import com.adrian.enums.SortAlgorithm;
import com.adrian.services.responses.SortResponse;
import com.adrian.services.responses.Status;

public abstract class AbstractSortService<T extends Comparable<T>> implements SortService<T> {

	@Override
	public SortResponse<T> sort(T[] array, BiPredicate<T, T> predicate) {
		SortResponse<T> response = new SortResponse<T>();

		response.setInitialArray(array.clone());

		actualSort(array, predicate);

		response.setSortedArray(array);
		Status responseStatus = new Status();
		responseStatus.setCode(StatusConstants.OK_CODE);
		responseStatus.setText(StatusConstants.OK_MESSAGE);
		response.setStatus(responseStatus);
		response.setAlgorithmName(SortAlgorithm.INSERTION_SORT.name());
		return response;
	}

	/**
	 * Perform the sorting of the given array using the provided predicate.
	 * 
	 * @param array the array to be sorted
	 * @param predicate the predicate used for the sorting
	 */
	protected abstract void actualSort(T[] array, BiPredicate<T, T> predicate);
	
	/**
	 * Get the name of the sorting algorithm used.
	 * 
	 * @return name of sorting algorithm
	 */
	protected abstract String getAlgorithmName();
}