package com.adrian.services;

import java.util.function.BiPredicate;

import com.adrian.services.responses.SortResponse;
import com.adrian.services.responses.SortResponseWithSteps;

/**
 * Holds methods used to sort a generic array. 
 * 
 * @param <T>
 */
public interface SortService<T extends Comparable<T>> {
	SortResponse<T> sort(T[] array,  BiPredicate<T, T> predicate);

	SortResponseWithSteps<T> sortWithSteps(T[] array, BiPredicate<T, T> predicate);
}