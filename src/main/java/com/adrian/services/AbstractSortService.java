package com.adrian.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import com.adrian.constants.StatusConstants;
import com.adrian.services.responses.SortResponse;
import com.adrian.services.responses.SortResponseWithSteps;
import com.adrian.services.responses.Status;
import com.adrian.services.responses.Step;

public abstract class AbstractSortService<T extends Comparable<T>> implements SortService<T> {

	@Override
	public SortResponseWithSteps<T> sortWithSteps(T[] array, BiPredicate<T, T> predicate) {
		SortResponseWithSteps<T> response = new SortResponseWithSteps<T>();

		response.setInitialArray(array.clone());
		long startTime = System.currentTimeMillis();
		actualSortWithSteps(array, predicate, new ArrayList<Step>());
		response.setDuration(System.currentTimeMillis() - startTime);

		response.setSortedArray(array);
		Status responseStatus = new Status();
		responseStatus.setCode(StatusConstants.OK_CODE);
		responseStatus.setText(StatusConstants.OK_MESSAGE);
		response.setStatus(responseStatus);
		response.setAlgorithmName(getAlgorithmName());
		return response;
	}

	
	@Override
	public SortResponse<T> sort(T[] array, BiPredicate<T, T> predicate) {
		SortResponse<T> response = new SortResponse<T>();

		response.setInitialArray(array.clone());
		long startTime = System.currentTimeMillis();
		actualSort(array, predicate);
		response.setDuration(System.currentTimeMillis() - startTime);

		response.setSortedArray(array);
		Status responseStatus = new Status();
		responseStatus.setCode(StatusConstants.OK_CODE);
		responseStatus.setText(StatusConstants.OK_MESSAGE);
		response.setStatus(responseStatus);
		response.setAlgorithmName(getAlgorithmName());
		return response;
	}

	/**
	 * Perform the sorting of the given array using the provided predicate.
	 * 
	 * @param array
	 *            the array to be sorted
	 * @param predicate
	 *            the predicate used for the sorting
	 */
	protected abstract void actualSort(T[] array, BiPredicate<T, T> predicate);
	
	/**
	 * Perform the sorting of the given array using the provided predicate and also records the steps used.
	 * 
	 * @param array
	 * @param predicate
	 * @param steps
	 */
	protected abstract void actualSortWithSteps(T[] array, BiPredicate<T, T> predicate, List<Step> steps);

	/**
	 * Get the name of the sorting algorithm used.
	 * 
	 * @return name of sorting algorithm
	 */
	protected abstract String getAlgorithmName();
}