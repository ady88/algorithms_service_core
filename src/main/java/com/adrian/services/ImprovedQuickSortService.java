package com.adrian.services;

import com.adrian.constants.SortConstants;
import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.ImprovedQuickSort;
import com.adrian.utils.AlgorithmsUtil;

@ImprovedQuickSort
public class ImprovedQuickSortService<T extends Comparable<T>> extends QuickSortService<T> {

	@Override
	protected void sort(T[] array, int low, int high) {
		if (high <= low + SortConstants.SORT_SWITCH) {
			AlgorithmsUtil.insertionSort(array, low, high, predicate);
			return;
		}

		int j = partition(array, low, high);
		sort(array, low, j - 1);
		sort(array, j + 1, high);
	}

	@Override
	protected String getAlgorithmName() {
		return SortAlgorithm.IMPROVED_QUICK_SORT.name();
	}
}
