package com.adrian.utils;

import java.util.function.BiPredicate;

public class AlgorithmsUtil<T> {

	/**
	 * Exchange element on positionA with element in positionB in the given array.
	 * 
	 * @param array
	 *            the array where element from position a is exchanged with element
	 *            from position b
	 * @param positionA
	 *            first index
	 * @param positionB
	 *            second index
	 */
	public static <T> void exchange(T[] array, int positionA, int positionB) {
		T aux = array[positionA];
		array[positionA] = array[positionB];
		array[positionB] = aux;
	}

	/**
	 * Validate the given array is sorted using the given predicate.
	 * 
	 * @param array
	 *            the array to check
	 * @param predicate
	 *            the predicate used to determine if the array is sorted
	 * @return true if the array is sorted, false otherwise
	 */
	public static <T> boolean validateSort(T[] array, BiPredicate<T, T> predicate) {
		if (array == null || array.length == 0) {
			return true;
		}

		boolean result = true;
		for (int i = 1; i < array.length; i++) {
			if (predicate.test(array[i], array[i - 1])) {
				result = false;
				break;
			}
		}

		return result;
	}

	/**
	 * Sort the given array from index start to index end using the given predicate.
	 * 
	 * @param array
	 *            the array to sort
	 * @param start
	 *            the start index
	 * @param end
	 *            the end index
	 * @param predicate
	 *            the predicate used for sorting
	 */
	public static <T> void insertionSort(T[] array, int start, int end, BiPredicate<T, T> predicate) {
		for (int i = start + 1; i < end + 1; i++) {
			for (int j = i; j > start && predicate.test(array[j], array[j - 1]); j--) {
				AlgorithmsUtil.exchange(array, j, j - 1);
			}
		}
	}
}