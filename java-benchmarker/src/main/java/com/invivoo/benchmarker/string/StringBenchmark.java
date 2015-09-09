package com.invivoo.benchmarker.string;

import com.invivoo.benchmarker.AbstractBenchmark;

public final class StringBenchmark extends AbstractBenchmark<Object> {
	private final int nb;

	public StringBenchmark(int nb) {
		this.nb = nb;
	}

	public Object call() throws Exception {
		String result = "";
		for (int i = 0; i < nb; i++) {
			result += "1";
		}
		return result;
	}

	public String getDescription() {
		return "String(" + nb + ")";
	}
}