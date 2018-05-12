package com.adrian.services;

import java.util.function.BiPredicate;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.ShellSort;
import com.adrian.services.responses.SortResponseWithSteps;
import com.adrian.utils.AlgorithmsUtil;

@ShellSort
public class ShellSortService<T extends Comparable<T>> extends AbstractSortService<T> {

	@Override
	public SortResponseWithSteps<T> sortWithSteps(T[] array, BiPredicate<T, T> predicate) {
		return null;
	}

	@Override
	protected void actualSort(T[] array, BiPredicate<T, T> predicate) {
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
	}

	@Override
	protected String getAlgorithmName() {
		return SortAlgorithm.SHELL_SORT.name();
	}
}
