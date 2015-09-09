package com.invivoo.benchmarker;

import java.util.concurrent.TimeUnit;

public abstract class AbstractBenchmark<V> implements Benchmark<V> {

	private long timeSpent;
	private long memoryConsumed;
	private long tempMemory;

	public final void bench() {
		// ensuring mem is clean
		Runtime runtime = Runtime.getRuntime();
		runtime.gc();

		// measuring
		long beginTime = System.nanoTime();
		long beginMem = runtime.totalMemory() - runtime.freeMemory();
		Object res = null;
		try {
			res = call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		long endMem0 = runtime.totalMemory() - runtime.freeMemory();
		// computing what gc will free to identify temp memory consumed
		runtime.gc();
		long endMem1 = runtime.totalMemory() - runtime.freeMemory();

		// displaying
		timeSpent = (endTime - beginTime);
		memoryConsumed = -(beginMem - endMem0);
		tempMemory = -(endMem1 - endMem0);
	}
	
	public long getTempMemory() {
		return tempMemory;
	}
	
	public long getMemoryConsumed() {
		return memoryConsumed;
	}
	
	public long getTimeSpent() {
		return timeSpent;
	}
	
	public String getDisplayTimeSpent() {
		if (TimeUnit.SECONDS.convert(timeSpent, TimeUnit.NANOSECONDS) > 0) {
			long nbMillis = TimeUnit.MILLISECONDS.convert(timeSpent, TimeUnit.NANOSECONDS); 
			return (nbMillis/1000) +"." + (nbMillis%1000)+" s";
		} else if (TimeUnit.MILLISECONDS.convert(timeSpent, TimeUnit.NANOSECONDS) > 0) {
			return (timeSpent/1000000) +"." +(timeSpent%1000000)+" ms";
		} else {
			return timeSpent +" ns";
		}
	}
	
	@Override
	public String toString() {
		return "<"+getDescription()+" time="+" used memory="+memoryConsumed+" temp memory="+tempMemory;
//		if (TimeUnit.SECONDS.convert(timeSpent, TimeUnit.NANOSECONDS) > 0) {
//			long nbMillis = TimeUnit.MILLISECONDS.convert(timeSpent, TimeUnit.NANOSECONDS); 
//			System.out.println("bench for " + benchmark.getDescription() + " time="+ (nbMillis/1000) +"." +(nbMillis%1000)+" s");
//		} else if (TimeUnit.MILLISECONDS.convert(timeSpent, TimeUnit.NANOSECONDS) > 0) {
//			System.out.println("bench for " + benchmark.getDescription() + " time="+ (timeSpent/1000000) +"." +(timeSpent%1000000)+" ms");
//		} else {
//			System.out.println("bench for " + benchmark.getDescription() + " time="+ timeSpent +" ns consumed");
//		}
//		System.out.println("bench for " + benchmark.getDescription() + " mem0="+memoryConsumed+" bytes consumed");
//		System.out.println("bench for " + benchmark.getDescription() + " mem1="+(memoryConsumed - tempMemory)+" bytes consumed gc="+tempMemory+" bytes garbage collected");
//		return display;
	}
}