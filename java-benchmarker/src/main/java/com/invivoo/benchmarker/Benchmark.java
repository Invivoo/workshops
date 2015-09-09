package com.invivoo.benchmarker;

import java.util.concurrent.Callable;

public interface Benchmark<V> extends Callable<V> {
	public void bench();
	
	String getDescription();
	
	public long getTempMemory();
	
	public long getMemoryConsumed();
	
	public long getTimeSpent();
	
	public String getDisplayTimeSpent();
}