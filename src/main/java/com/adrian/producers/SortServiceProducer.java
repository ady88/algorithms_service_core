package com.adrian.producers;

import javax.enterprise.inject.Produces;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.ImprovedMergeSort;
import com.adrian.qualifiers.ImprovedQuickSort;
import com.adrian.qualifiers.InsertionSort;
import com.adrian.qualifiers.MergeSort;
import com.adrian.qualifiers.QuickSort;
import com.adrian.qualifiers.SelectionSort;
import com.adrian.qualifiers.ShellSort;
import com.adrian.services.SortService;

public class SortServiceProducer<T> {

	private static final SortAlgorithm SORT_ALGORITHM = SortAlgorithm.IMPROVED_QUICK_SORT;

	@SuppressWarnings("rawtypes")
	@Produces
	public SortService getSortService(@SelectionSort SortService<?> selectionSort,
			@InsertionSort SortService<?> insertionSort, @ShellSort SortService<?> shellsort,
			@MergeSort SortService<?> mergeSort, @ImprovedMergeSort SortService<?> improvedMergeSort,
			@QuickSort SortService<?> quickSort, @ImprovedQuickSort SortService<?> improvedQuickSort) {
		SortService<?> result;

		switch (SORT_ALGORITHM) {
		case INSERTION_SORT:
			result = insertionSort;
			break;
		case SHELL_SORT:
			result = shellsort;
			break;
		case MERGE_SORT:
			result = mergeSort;
			break;
		case IMPROVED_MERGE_SORT:
			result = improvedMergeSort;
			break;
		case QUICK_SORT:
			result = quickSort;
			break;
		case IMPROVED_QUICK_SORT:
			result = improvedQuickSort;
			break;
		case SELECTION_SORT:
		default:
			result = selectionSort;
		}

		return result;
	}

	@SuppressWarnings("rawtypes")
	@Produces
	@InsertionSort
	public SortService getInsertionSort(@InsertionSort SortService<?> insertionSort) {
		return insertionSort;
	}

	@SuppressWarnings("rawtypes")
	@Produces
	@SelectionSort
	public SortService getSelectionSort(@SelectionSort SortService<?> selectionSort) {
		return selectionSort;
	}

	@SuppressWarnings("rawtypes")
	@Produces
	@ShellSort
	public SortService getShellSort(@ShellSort SortService<?> shellSort) {
		return shellSort;
	}

	@SuppressWarnings("rawtypes")
	@Produces
	@MergeSort
	public SortService getMergeSort(@MergeSort SortService<?> mergeSort) {
		return mergeSort;
	}

	@SuppressWarnings("rawtypes")
	@Produces
	@ImprovedMergeSort
	public SortService getImprovedMergeSort(@ImprovedMergeSort SortService<?> improvedMergeSort) {
		return improvedMergeSort;
	}

	@SuppressWarnings("rawtypes")
	@Produces
	@QuickSort
	public SortService getQuickSort(@QuickSort SortService<?> quickSort) {
		return quickSort;
	}

	@SuppressWarnings("rawtypes")
	@Produces
	@ImprovedQuickSort
	public SortService getImprovedQuickSort(@ImprovedQuickSort SortService<?> improvedQuickSort) {
		return improvedQuickSort;
	}
}
