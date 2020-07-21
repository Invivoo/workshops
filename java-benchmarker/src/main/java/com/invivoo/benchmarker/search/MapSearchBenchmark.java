package com.invivoo.benchmarker.search;

import java.util.HashMap;
import java.util.Map;

public class MapSearchBenchmark extends SearchBenchmark {
	private final Map<Object, Object> l = new HashMap<Object, Object>();

	public MapSearchBenchmark(Object unknown) {
		super(unknown);
	}	

	public MapSearchBenchmark(Object[] content, Object unknown) {
		super(unknown);
		init(content);
	}

	@Override
	protected void add(Object o) {
		l.put(o, this);
	}
	
	@Override
	protected boolean searchFor(Object object) {
		return l.containsKey(object);
	}

	
	public String getDescription() {
		return "MapSearchBenchmark(" + l.size() + ")";
	}

}
