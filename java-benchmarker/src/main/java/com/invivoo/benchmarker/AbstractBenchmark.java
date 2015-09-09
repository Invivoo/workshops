package com.invivoo.benchmarker;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public abstract class AbstractBenchmark<V> implements Benchmark<V> {
	final BenchValue values = new BenchValue();
	
	public final void bench(final Object param) {
		values.reset();
		if (param != null) {
			measure(new Callable<Object>() {

				public Object call() throws Exception {
					init(param);
					return null;
				}
			});
		}
		measure(this);
	}
	
	protected void init(Object param) {
	}

	private final void measure(Callable c) {
		// ensuring mem is clean
		Runtime runtime = Runtime.getRuntime();
		runtime.gc();

		// measuring
		long beginTime = System.nanoTime();
		long beginMem = runtime.totalMemory() - runtime.freeMemory();
		Object res = null;
		try {
			res = c.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		long endMem0 = runtime.totalMemory() - runtime.freeMemory();
		// computing what gc will free to identify temp memory consumed
		runtime.gc();
		long endMem1 = runtime.totalMemory() - runtime.freeMemory();

		// displaying
		values.timeSpent += (endTime - beginTime);
		values.memoryConsumed += -(beginMem - endMem0);
		values.tempMemory += -(endMem1 - endMem0);
	}
	
	
	public long getTempMemory() {
		return values.tempMemory;
	}
	
	public long getMemoryConsumed() {
		return values.memoryConsumed;
	}
	
	public long getTimeSpent() {
		return values.timeSpent;
	}
	
	public String getDisplayTimeSpent() {
		if (TimeUnit.SECONDS.convert(values.timeSpent, TimeUnit.NANOSECONDS) > 0) {
			long nbMillis = TimeUnit.MILLISECONDS.convert(values.timeSpent, TimeUnit.NANOSECONDS); 
			return (nbMillis/1000) +"." + (nbMillis%1000)+" s";
		} else if (TimeUnit.MILLISECONDS.convert(values.timeSpent, TimeUnit.NANOSECONDS) > 0) {
			return (values.timeSpent/1000000) +"." +(values.timeSpent%1000000)+" ms";
		} else {
			return values.timeSpent +" ns";
		}
	}
	
	@Override
	public String toString() {
		return "<"+getDescription()+" time="+" used memory="+values.memoryConsumed+" temp memory="+values.tempMemory;
	}
	
	final class BenchValue {
		long timeSpent;
		long memoryConsumed;
		long tempMemory;
		
		public void reset() {
			timeSpent = 0;
			memoryConsumed = 0;
			tempMemory = 0;
		}

	
	}
}