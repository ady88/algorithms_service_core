package com.adrian.services;

import com.adrian.constants.SortConstants;
import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.ImprovedMergeSort;
import com.adrian.utils.AlgorithmsUtil;

@ImprovedMergeSort
public class ImprovedMergeSortService<T extends Comparable<T>> extends MergeSortService<T> {

	@Override
	protected void sort(T[] array, int low, int high) {
		if (high <= low + SortConstants.SORT_SWITCH) {
			AlgorithmsUtil.insertionSort(array, low, high, predicate);
			return;
		}

		int middle = low + (high - low) / 2;
		sort(array, low, middle);
		sort(array, middle + 1, high);

		if (predicate.test(array[middle + 1], array[middle])) {
			merge(array, low, middle, high);
		}
	}

	@Override
	protected String getAlgorithmName() {
		return SortAlgorithm.IMPROVED_MERGE_SORT.name();
	}
}