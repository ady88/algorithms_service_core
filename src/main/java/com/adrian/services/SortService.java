package com.adrian.services;

import com.adrian.services.responses.SortResponse;
import com.adrian.services.responses.SortResponseWithSteps;

/**
 * Holds methods used to sort a generic array. 
 * 
 * @param <T>
 */
public interface SortService<T> {
	SortResponse<T> sort(T[] array);

	SortResponseWithSteps<T> sortWithSteps(T[] array);
}