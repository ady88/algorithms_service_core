package com.adrian.utils;

import java.util.function.BiPredicate;

public class AlgorithmsUtil<T> {

	public static <T> void exchange(T[] array, int positionA, int positionB) {
		T aux = array[positionA];
		array[positionA] = array[positionB];
		array[positionB] = aux;
	}

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

	public static <T> void insertionSort(T[] array, int start, int end, BiPredicate<T, T> predicate) {
		for (int i = start + 1; i < end + 1; i++) {
			for (int j = i; j > start && predicate.test(array[j], array[j - 1]); j--) {
				AlgorithmsUtil.exchange(array, j, j - 1);
			}
		}
	}
}