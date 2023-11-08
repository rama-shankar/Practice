package com.rs.problems;// Java code to show the implementation of
// Collectors partitioningBy() function

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PartitioningBy {

	// Driver code
	public static void main(String[] args)
	{
		// creating an Integer stream
		Stream<Boolean>
			s = Stream.of(false, false, false);

		// using Collectors partitioningBy()
		// method to split the stream of elements into
		// 2 parts, greater than 3 and less than 3.
		Map<Boolean, List<Boolean> >
			map = s.collect(
				Collectors.partitioningBy(num -> Boolean.TRUE.equals(num)));

		// Displaying the result as a map
		// true if greater than 3, false otherwise
		System.out.println(map.get(Boolean.TRUE) == null);
	}
}
