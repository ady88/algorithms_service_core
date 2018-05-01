package com.adrian.services;

import java.util.function.BiPredicate;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.SelectionSort;
import com.adrian.services.responses.SortResponse;
import com.adrian.services.responses.SortResponseWithSteps;
import com.adrian.services.responses.Status;
import com.adrian.utils.AlgorithmsUtil;

@SelectionSort
public class SelectionSortService<T extends Comparable<T>> implements SortService<T> {

	@Override
	public SortResponse<T> sort(T[] array, BiPredicate<T, T> predicate) {
		SortResponse<T> response = new SortResponse<T>();

		response.setInitialArray(array.clone());
		int length = array.length;

		for (int i = 0; i < length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < length; j++) {
				if (predicate.test(array[j], array[min])) {
					min = j;
				}
			}
			AlgorithmsUtil.exchange(array, min, i);
		}

		response.setSortedArray(array);
		Status responseStatus = new Status();
		responseStatus.setCode(200);
		responseStatus.setText("OK");
		response.setStatus(responseStatus);
		response.setAlgorithmName(SortAlgorithm.SELECTION_SORT.name());
		return response;
	}

	@Override
	public SortResponseWithSteps<T> sortWithSteps(T[] array, BiPredicate<T, T> predicate) {
		return null;
	}
}
