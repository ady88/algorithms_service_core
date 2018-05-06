package com.adrian.services;

import java.util.function.BiPredicate;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.MergeSort;
import com.adrian.services.responses.SortResponse;
import com.adrian.services.responses.SortResponseWithSteps;
import com.adrian.services.responses.Status;

@MergeSort
public class MergeSortService<T extends Comparable<T>> implements SortService<T> {

	private T[] aux;
	BiPredicate<T, T> predicate;

	@SuppressWarnings("unchecked")
	@Override
	public SortResponse<T> sort(T[] array, BiPredicate<T, T> predicate) {
		SortResponse<T> response = new SortResponse<T>();

		response.setInitialArray(array.clone());
		int length = array.length;
		aux = ((T[]) new Comparable[length]);
		this.predicate = predicate;
		sort(array, 0, length - 1);

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
	
	protected void merge(T[] array, int low, int middle, int high) {
		// merge array[low...middle] with array[middle + 1...high]

		int i = low;
		int j = middle + 1;

		for (int k = low; k <= high; k++) {
			aux[k] = array[k];
		}
		for (int k = low; k <= high; k++) {
			if (i > middle) {
				array[k] = aux[j++];
			} else if (j > high) {
				array[k] = aux[i++];
			} else if (predicate.test(aux[j], aux[i])) {
				array[k] = aux[j++];
			} else {
				array[k] = aux[i++];
			}
		}
	}

	protected void sort(T[] array, int low, int high) {
		if (high <= low) {
			return;
		}

		int middle = low + (high - low) / 2;
		sort(array, low, middle);
		sort(array, middle + 1, high);
		merge(array, low, middle, high);
	}
}
