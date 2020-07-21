package com.invivoo.benchmarker.search;

import java.util.ArrayList;
import java.util.List;

public class SimpleListSearchBenchmark extends SearchBenchmark {
	private final List<Object> l = new ArrayList<Object>();

	public SimpleListSearchBenchmark(Object unknown) {
		super(unknown);
	}
	
	public SimpleListSearchBenchmark(Object[] content, Object unknown) {
		super(unknown);
		init(content);
	}
	
	@Override
	protected void add(Object o) {
		l.add(o);
	}
		
	@Override
	protected boolean searchFor(Object object) {
		return l.contains(object);
	}
	
	public String getDescription() {
		return "SimpleListSearchBenchmark(" + l.size() + ")";
	}

}
