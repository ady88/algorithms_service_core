package com.adrian.services;

import java.util.function.BiPredicate;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.InsertionSort;
import com.adrian.services.responses.SortResponse;
import com.adrian.services.responses.SortResponseWithSteps;
import com.adrian.services.responses.Status;
import com.adrian.utils.AlgorithmsUtil;

@InsertionSort
public class InsertionSortService<T extends Comparable<T>> implements SortService<T> {

	@Override
	public SortResponse<T> sort(T[] array, BiPredicate<T, T> predicate) {
		SortResponse<T> response = new SortResponse<T>();

		response.setInitialArray(array.clone());
		int length = array.length;

		for (int i = 1; i < length; i++) {
			for (int j = i; j > 0 && predicate.test(array[j], array[j - 1]); j--) {
				AlgorithmsUtil.exchange(array, j - 1, j);
			}

		}

		response.setSortedArray(array);
		Status responseStatus = new Status();
		responseStatus.setCode(200);
		responseStatus.setText("OK");
		response.setStatus(responseStatus);
		response.setAlgorithmName(SortAlgorithm.INSERTION_SORT.name());
		return response;
	}

	@Override
	public SortResponseWithSteps<T> sortWithSteps(T[] array, BiPredicate<T, T> predicate) {
		// TODO Auto-generated method stub
		return null;
	}
}