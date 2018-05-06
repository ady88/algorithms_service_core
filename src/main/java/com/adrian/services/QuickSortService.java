package com.adrian.services;

import java.util.function.BiPredicate;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.QuickSort;
import com.adrian.services.responses.SortResponse;
import com.adrian.services.responses.SortResponseWithSteps;
import com.adrian.services.responses.Status;
import com.adrian.utils.AlgorithmsUtil;

@QuickSort
public class QuickSortService<T extends Comparable<T>> implements SortService<T> {

	BiPredicate<T, T> predicate;

	@Override
	public SortResponse<T> sort(T[] array, BiPredicate<T, T> predicate) {
		SortResponse<T> response = new SortResponse<T>();
		response.setInitialArray(array.clone());
		this.predicate = predicate;

		sort(array, 0, array.length - 1);

		response.setSortedArray(array);
		Status responseStatus = new Status();
		responseStatus.setCode(200);
		responseStatus.setText("OK");
		response.setStatus(responseStatus);
		response.setAlgorithmName(SortAlgorithm.MERGE_SORT.name());
		return response;
	}

	@Override
	public SortResponseWithSteps<T> sortWithSteps(T[] array, BiPredicate<T, T> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void sort(T[] array, int low, int high) {
		if (high <= low) {
			return;
		}

		int j = partition(array, low, high);
		sort(array, low, j - 1);
		sort(array, j + 1, high);
	}

	protected int partition(T[] array, int low, int high) {
		// merge array[low...middle] with array[middle + 1...high]

		int leftIndex = low;
		int rightIndex = high + 1;
		T partitionItem = array[low];

		while (true) {
			while (predicate.test(array[++leftIndex], partitionItem)) {
				if (leftIndex == high) {
					break;
				}
			}
			while (predicate.test(partitionItem, array[--rightIndex])) {
				if (rightIndex == low) {
					break;
				}
			}
			if (leftIndex >= rightIndex) {
				break;
			}

			AlgorithmsUtil.exchange(array, leftIndex, rightIndex);
		}

		AlgorithmsUtil.exchange(array, low, rightIndex);
		return rightIndex;
	}
}
