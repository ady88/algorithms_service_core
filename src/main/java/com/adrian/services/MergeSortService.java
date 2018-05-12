package com.adrian.services;

import java.util.function.BiPredicate;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.MergeSort;
import com.adrian.services.responses.SortResponseWithSteps;

@MergeSort
public class MergeSortService<T extends Comparable<T>> extends AbstractSortService<T> {

	private T[] aux;
	BiPredicate<T, T> predicate;

	@Override
	public SortResponseWithSteps<T> sortWithSteps(T[] array, BiPredicate<T, T> predicate) {
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

	@SuppressWarnings("unchecked")
	@Override
	protected void actualSort(T[] array, BiPredicate<T, T> predicate) {
		int length = array.length;
		aux = ((T[]) new Comparable[length]);
		this.predicate = predicate;
		sort(array, 0, length - 1);
		
	}

	@Override
	protected String getAlgorithmName() {
		return SortAlgorithm.MERGE_SORT.name();
	}
}
