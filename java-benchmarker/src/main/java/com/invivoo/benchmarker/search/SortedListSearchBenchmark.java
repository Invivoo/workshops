package com.invivoo.benchmarker.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortedListSearchBenchmark extends SearchBenchmark {
	private final List<Comparable<Object>> l = new ArrayList<Comparable<Object>>();

	public SortedListSearchBenchmark(Object unknown) {
		super(unknown);
	}	

	public SortedListSearchBenchmark(Comparable<Object>[] content, Object unknown) {
		super(unknown);
		init(content);
	}
	
	@Override
	protected void add(Object o) {
		int indexToAdd = Collections.binarySearch(l, o);
		if (indexToAdd > 0) {
			throw new RuntimeException();
		}
		l.add(-indexToAdd-1, (Comparable<Object>) o);
	}

	@Override
	protected boolean searchFor(Object object) {
		return Collections.binarySearch(l, object) >= 0;
	}
	
	public String getDescription() {
		return "SortedListSearchBenchmark(" + l.size() + ")";
	}

}
