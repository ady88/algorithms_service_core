package com.adrian.services;

import java.util.Arrays;
import java.util.Random;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

import javax.inject.Inject;

import com.adrian.enums.SortAlgorithm;
import com.adrian.qualifiers.ImprovedMergeSort;
import com.adrian.qualifiers.ImprovedQuickSort;
import com.adrian.qualifiers.InsertionSort;
import com.adrian.qualifiers.MergeSort;
import com.adrian.qualifiers.QuickSort;
import com.adrian.qualifiers.SelectionSort;
import com.adrian.qualifiers.ShellSort;

/**
 * Holds methods for generating a sort report with statistic information about
 * each sort.
 */
public class SortIntegersReportService {

	private static final int NUMBER_OF_RUNS = 10;
	private static final String DURATION_REPORT_MESSAGE = "#### %s \n\n The time in milliseconds for each run \n ";
	private static final String AVERAGE_TIME_MESSAGE = "\n##### Average time: %d ms \n";

	@SuppressWarnings("rawtypes")
	@Inject
	@InsertionSort
	private SortService insertionSort;

	@SuppressWarnings("rawtypes")
	@Inject
	@SelectionSort
	private SortService selectionSort;

	@SuppressWarnings("rawtypes")
	@Inject
	@ShellSort
	private SortService shellSort;

	@SuppressWarnings("rawtypes")
	@Inject
	@MergeSort
	private SortService mergeSort;

	@SuppressWarnings("rawtypes")
	@Inject
	@ImprovedMergeSort
	private SortService improvedMergeSort;

	@SuppressWarnings("rawtypes")
	@Inject
	@QuickSort
	private SortService quickSort;

	@SuppressWarnings("rawtypes")
	@Inject
	@ImprovedQuickSort
	private SortService improvedQuickSort;

	public String getReport(int arraySize, int lowerBoundElement, int upperBoundElement,
			BiPredicate<Integer, Integer> predicate) {
		var random = new Random();

		StringBuilder sortReportBuilder = new StringBuilder(String.format(
				"# SortReport \n\n###General information: \n _Size of the sorted array: %d_ \n _Number of runs: %d_ \n _Lower bound array element: %d_ \n _Upper bound array element: %d_ \n---\n",
				arraySize, NUMBER_OF_RUNS, lowerBoundElement, upperBoundElement));

		StringBuilder insertionSortReportBuilder = new StringBuilder(
				String.format(DURATION_REPORT_MESSAGE, SortAlgorithm.INSERTION_SORT.name()));
		StringBuilder selectionSortReportBuilder = new StringBuilder(
				String.format(DURATION_REPORT_MESSAGE, SortAlgorithm.SELECTION_SORT.name()));
		StringBuilder shellSortReportBuilder = new StringBuilder(
				String.format(DURATION_REPORT_MESSAGE, SortAlgorithm.SHELL_SORT.name()));
		StringBuilder mergeSortReportBuilder = new StringBuilder(
				String.format(DURATION_REPORT_MESSAGE, SortAlgorithm.MERGE_SORT.name()));
		StringBuilder improvedMergeSortReportBuilder = new StringBuilder(
				String.format(DURATION_REPORT_MESSAGE, SortAlgorithm.IMPROVED_MERGE_SORT.name()));
		StringBuilder quickSortReportBuilder = new StringBuilder(
				String.format(DURATION_REPORT_MESSAGE, SortAlgorithm.QUICK_SORT.name()));
		StringBuilder improvedQuickSortReportBuilder = new StringBuilder(
				String.format(DURATION_REPORT_MESSAGE, SortAlgorithm.IMPROVED_QUICK_SORT.name()));
		StringBuilder javaArraysSortReportBuilder = new StringBuilder(
				String.format(DURATION_REPORT_MESSAGE, "java.util.Arrays.sort"));

		long insertionSortTotalDuration = 0;
		long selectionSortTotalDuration = 0;
		long shellSortTotalDuration = 0;
		long mergeSortTotalDuration = 0;
		long improvedMergeSortTotalDuration = 0;
		long quickSortTotalDuration = 0;
		long improvedQuickSortTotalDuration = 0;
		long javaArraysSortTotalDuration = 0;
		for (int i = 0; i < NUMBER_OF_RUNS; i++) {
			var unboxedArray = random.ints(arraySize, lowerBoundElement, upperBoundElement).toArray();

			var array = IntStream.of(unboxedArray).boxed().toArray(Integer[]::new);

			@SuppressWarnings("unchecked")
			var insertionSortResponse = insertionSort.sort(array.clone(), predicate);

			@SuppressWarnings("unchecked")
			var shellSortResponse = shellSort.sort(array.clone(), predicate);

			@SuppressWarnings("unchecked")
			var selectionSortResponse = selectionSort.sort(array.clone(), predicate);

			@SuppressWarnings("unchecked")
			var mergeSortResponse = mergeSort.sort(array.clone(), predicate);

			@SuppressWarnings("unchecked")
			var improvedMergeSortResponse = improvedMergeSort.sort(array.clone(), predicate);

			@SuppressWarnings("unchecked")
			var quickSortResponse = quickSort.sort(array.clone(), predicate);

			@SuppressWarnings("unchecked")
			var improvedQuickSortResponse = improvedQuickSort.sort(array.clone(), predicate);

			long javaSortStartTime = System.currentTimeMillis();
			Arrays.sort(array.clone());
			long javaSortDuration = System.currentTimeMillis() - javaSortStartTime;
			javaArraysSortReportBuilder.append(String.format(" %d &nbsp;&nbsp;&nbsp; ", javaSortDuration));

			insertionSortReportBuilder
					.append(String.format(" %d &nbsp;&nbsp;&nbsp; ", insertionSortResponse.getDuration()));
			selectionSortReportBuilder
					.append(String.format(" %d &nbsp;&nbsp;&nbsp; ", selectionSortResponse.getDuration()));
			shellSortReportBuilder.append(String.format(" %d &nbsp;&nbsp;&nbsp; ", shellSortResponse.getDuration()));
			mergeSortReportBuilder.append(String.format(" %d &nbsp;&nbsp;&nbsp; ", mergeSortResponse.getDuration()));
			improvedMergeSortReportBuilder
					.append(String.format(" %d &nbsp;&nbsp;&nbsp; ", improvedMergeSortResponse.getDuration()));
			quickSortReportBuilder.append(String.format(" %d &nbsp;&nbsp;&nbsp; ", quickSortResponse.getDuration()));
			improvedQuickSortReportBuilder
					.append(String.format(" %d &nbsp;&nbsp;&nbsp; ", improvedQuickSortResponse.getDuration()));

			insertionSortTotalDuration += insertionSortResponse.getDuration();
			selectionSortTotalDuration += selectionSortResponse.getDuration();
			shellSortTotalDuration += shellSortResponse.getDuration();
			mergeSortTotalDuration += mergeSortResponse.getDuration();
			improvedMergeSortTotalDuration += improvedMergeSortResponse.getDuration();
			quickSortTotalDuration += quickSortResponse.getDuration();
			improvedQuickSortTotalDuration += improvedQuickSortResponse.getDuration();
			javaArraysSortTotalDuration += javaSortDuration;
		}

		insertionSortReportBuilder
				.append(String.format(AVERAGE_TIME_MESSAGE, insertionSortTotalDuration / NUMBER_OF_RUNS));
		selectionSortReportBuilder
				.append(String.format(AVERAGE_TIME_MESSAGE, selectionSortTotalDuration / NUMBER_OF_RUNS));
		shellSortReportBuilder.append(String.format(AVERAGE_TIME_MESSAGE, shellSortTotalDuration / NUMBER_OF_RUNS));
		mergeSortReportBuilder.append(String.format(AVERAGE_TIME_MESSAGE, mergeSortTotalDuration / NUMBER_OF_RUNS));
		improvedMergeSortReportBuilder
				.append(String.format(AVERAGE_TIME_MESSAGE, improvedMergeSortTotalDuration / NUMBER_OF_RUNS));
		quickSortReportBuilder.append(String.format(AVERAGE_TIME_MESSAGE, quickSortTotalDuration / NUMBER_OF_RUNS));
		improvedQuickSortReportBuilder
				.append(String.format(AVERAGE_TIME_MESSAGE, improvedQuickSortTotalDuration / NUMBER_OF_RUNS));
		javaArraysSortReportBuilder.append(String.format(AVERAGE_TIME_MESSAGE, javaArraysSortTotalDuration / NUMBER_OF_RUNS));

		sortReportBuilder.append(insertionSortReportBuilder.toString());
		sortReportBuilder.append(selectionSortReportBuilder.toString());
		sortReportBuilder.append(shellSortReportBuilder.toString());
		sortReportBuilder.append(mergeSortReportBuilder.toString());
		sortReportBuilder.append(improvedMergeSortReportBuilder.toString());
		sortReportBuilder.append(quickSortReportBuilder.toString());
		sortReportBuilder.append(improvedQuickSortReportBuilder.toString());
		sortReportBuilder.append(javaArraysSortReportBuilder.toString());

		return sortReportBuilder.toString();
	}
}