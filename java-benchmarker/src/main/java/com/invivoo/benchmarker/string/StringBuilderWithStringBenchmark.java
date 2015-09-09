package com.invivoo.benchmarker.string;

import com.invivoo.benchmarker.AbstractBenchmark;

public final class StringBuilderWithStringBenchmark extends AbstractBenchmark<Object> {
	private final int length;
	private final int defSize;
	public StringBuilderWithStringBenchmark(int length, int defSize) {
		this.length = length;
		this.defSize = defSize;
	}
	
	public Object call() throws Exception {
		StringBuilder result = new StringBuilder(defSize);
		for (int i = 0; i < length; i++) {
			result.append("1");
		}
		return result.toString();
	}

	public String getDescription() {
		return "StringBuilder String ("+length+"/"+defSize+")";
	}
}
