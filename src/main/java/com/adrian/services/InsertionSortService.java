package com.adrian.services;

import java.util.List;
import java.util.function.BiPredicate;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.InsertionSort;
import com.adrian.services.responses.SortResponseWithSteps;
import com.adrian.services.responses.Step;
import com.adrian.utils.AlgorithmsUtil;

@InsertionSort
public class InsertionSortService<T extends Comparable<T>> extends AbstractSortService<T> {

	@Override
	protected void actualSortWithSteps(T[] array, BiPredicate<T, T> predicate, List<Step> steps) {
		// TODO Auto-generated method stub

	}

	@Override
	public SortResponseWithSteps<T> sortWithSteps(T[] array, BiPredicate<T, T> predicate) {
		return null;
	}

	@Override
	protected void actualSort(T[] array, BiPredicate<T, T> predicate) {
		int length = array.length;

		for (int i = 1; i < length; i++) {
			for (int j = i; j > 0 && predicate.test(array[j], array[j - 1]); j--) {
				AlgorithmsUtil.exchange(array, j - 1, j);
			}
		}
	}

	@Override
	protected String getAlgorithmName() {
		return SortAlgorithm.INSERTION_SORT.name();
	}
}