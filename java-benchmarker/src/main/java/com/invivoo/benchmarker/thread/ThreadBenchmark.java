package com.invivoo.benchmarker.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.invivoo.benchmarker.AbstractBenchmark;

public final class ThreadBenchmark extends AbstractBenchmark<Object> {
	private final int nbTask;
	private final int nbThread;
	private final Callable<Object> benchToExcute;

	public ThreadBenchmark(int nbTask, int nbThread, Callable<Object> benchToExcute) {
		this.nbThread = nbThread;
		this.nbTask = nbTask;
		this.benchToExcute = benchToExcute;
	}

	public Object call() throws Exception {
		ExecutorService threadPool = Executors.newFixedThreadPool(nbThread);
		for (int i = 0; i < nbTask; i++) {
			threadPool.submit(benchToExcute);
		}
		threadPool.shutdown();
		threadPool.awaitTermination(1, TimeUnit.DAYS);
		return null;
	}

	public String getDescription() {
		return "Thread-"+benchToExcute + "(nb task=" + nbTask + " nb thread=" + nbThread + ")";
	}
}