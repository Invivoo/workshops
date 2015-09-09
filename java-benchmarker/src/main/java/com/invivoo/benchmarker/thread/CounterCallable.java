package com.invivoo.benchmarker.thread;

import java.util.concurrent.Callable;

public final class CounterCallable implements Callable<Object> {
	private final int nb;

	public CounterCallable(int nb) {
		this.nb = nb;
	}
	
	public Object call() throws Exception {
		int result = 0;
		for (int i = 0; i < nb; i++) {
			result ++;
		}
		return result;
	}

	@Override
	public String toString() {
		return "Counter("+nb+")";
	}
}

