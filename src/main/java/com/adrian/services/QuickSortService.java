package com.adrian.services;

import java.util.List;
import java.util.function.BiPredicate;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.QuickSort;
import com.adrian.services.responses.Step;
import com.adrian.utils.AlgorithmsUtil;

@QuickSort
public class QuickSortService<T extends Comparable<T>> extends AbstractSortService<T> {

	protected BiPredicate<T, T> predicate;

	@Override
	protected void actualSortWithSteps(T[] array, BiPredicate<T, T> predicate, List<Step> steps) {
		// TODO Auto-generated method stub
		
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

	@Override
	protected void actualSort(T[] array, BiPredicate<T, T> predicate) {
		this.predicate = predicate;
		sort(array, 0, array.length - 1);
	}

	@Override
	protected String getAlgorithmName() {
		return SortAlgorithm.QUICK_SORT.name();
	}
}
