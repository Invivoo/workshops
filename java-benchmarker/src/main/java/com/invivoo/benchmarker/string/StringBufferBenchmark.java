package com.invivoo.benchmarker.string;

import com.invivoo.benchmarker.AbstractBenchmark;

public final class StringBufferBenchmark extends AbstractBenchmark<Object> {
	private final int length;
	private final int defSize;
	
	public StringBufferBenchmark(int length, int defSize) {
		this.length = length;
		this.defSize = defSize;
	}
	
	public Object call() throws Exception {
		StringBuffer result = new StringBuffer(defSize);
		for (int i = 0; i < length; i++) {
			result.append("1");
		}
		return result.toString();
	}

	public String getDescription() {
		return "StringBuffer("+length+"/"+defSize+")";
	}
}