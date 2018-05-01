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
}