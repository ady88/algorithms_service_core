package com.adrian.services;

import java.util.function.BiPredicate;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.SelectionSort;
import com.adrian.services.responses.SortResponseWithSteps;
import com.adrian.utils.AlgorithmsUtil;

@SelectionSort
public class SelectionSortService<T extends Comparable<T>> extends AbstractSortService<T> {

	@Override
	public SortResponseWithSteps<T> sortWithSteps(T[] array, BiPredicate<T, T> predicate) {
		return null;
	}

	@Override
	protected void actualSort(T[] array, BiPredicate<T, T> predicate) {
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
	}

	@Override
	protected String getAlgorithmName() {
		return SortAlgorithm.SELECTION_SORT.name();
	}
}
