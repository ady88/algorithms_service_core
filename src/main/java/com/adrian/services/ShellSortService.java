package com.adrian.services;

import java.util.function.BiPredicate;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.ShellSort;
import com.adrian.services.responses.SortResponse;
import com.adrian.services.responses.SortResponseWithSteps;
import com.adrian.services.responses.Status;
import com.adrian.utils.AlgorithmsUtil;

@ShellSort
public class ShellSortService<T extends Comparable<T>> implements SortService<T> {

	@Override
	public SortResponse<T> sort(T[] array, BiPredicate<T, T> predicate) {
		SortResponse<T> response = new SortResponse<T>();

		response.setInitialArray(array.clone());
		int length = array.length;

		int step = 1;

		while (step < length / 3) {
			step = step * 3 + 1;
		}
		while (step >= 1) {

			for (int i = step; i < length; i++) {
				for (int j = i; j >= step && predicate.test(array[j], array[j - step]); j -= step) {
					AlgorithmsUtil.exchange(array, j - step, j);
				}
			}

			step = step / 3;
		}

		response.setSortedArray(array);
		Status responseStatus = new Status();
		responseStatus.setCode(200);
		responseStatus.setText("OK");
		response.setStatus(responseStatus);
		response.setAlgorithmName(SortAlgorithm.SHELL_SORT.name());
		return response;
	}

	@Override
	public SortResponseWithSteps<T> sortWithSteps(T[] array, BiPredicate<T, T> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

}
