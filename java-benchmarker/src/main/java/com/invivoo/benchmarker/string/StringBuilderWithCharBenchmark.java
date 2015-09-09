package com.invivoo.benchmarker.string;

import com.invivoo.benchmarker.AbstractBenchmark;

public final class StringBuilderWithCharBenchmark extends AbstractBenchmark<Object> {
	private final int length;
	private final int defSize;

	public StringBuilderWithCharBenchmark(int length) {
		this(length, length);
	}
	
	public StringBuilderWithCharBenchmark(int length, int defSize) {
		this.length = length;
		this.defSize = defSize;
	}
	
	public Object call() throws Exception {
		StringBuilder result = new StringBuilder(defSize);
		for (int i = 0; i < length; i++) {
			result.append('1');
		}
		return result;
	}

	public String getDescription() {
		return "StringBuilder char *("+length+"/"+defSize+")";
	}
}