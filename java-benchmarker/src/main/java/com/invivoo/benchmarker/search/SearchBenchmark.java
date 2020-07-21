package com.invivoo.benchmarker.search;

import com.invivoo.benchmarker.AbstractBenchmark;

public abstract class SearchBenchmark extends AbstractBenchmark<Object> {
	private Object[] content;
	private final Object unknown;

	public SearchBenchmark(Object unknown) {
		this.unknown = unknown;
	}
	
	@Override
	protected final void init(Object param) {
		content = (Object[]) param;
		for (int i = 0; i < content.length; i++) {
			add(content[i]);
		}
	}
	
	protected abstract void add(Object o);

	public final Object call() throws Exception {
		for (int i = 0; i < content.length; i++) {
			boolean found = searchFor(content[i]);
			if (! found) {
				throw new RuntimeException();
			}
		}
		for (int i = 0; i < content.length; i++) {
			boolean found = searchFor(unknown);
			if (found) {
				throw new RuntimeException();
			}
		}
		return null;
	}

	protected abstract boolean searchFor(Object object);
}
