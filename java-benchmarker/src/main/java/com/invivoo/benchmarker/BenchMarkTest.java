package com.invivoo.benchmarker;

import com.invivoo.benchmarker.string.StringBenchmark;
import com.invivoo.benchmarker.string.StringBufferBenchmark;
import com.invivoo.benchmarker.string.StringBuilderWithCharBenchmark;
import com.invivoo.benchmarker.string.StringBuilderWithStringBenchmark;
import com.invivoo.benchmarker.thread.CounterCallable;
import com.invivoo.benchmarker.thread.SleepCallable;
import com.invivoo.benchmarker.thread.ThreadBenchmark;

public class BenchMarkTest {
	public static void main(String[] args) {
		try {
			System.out.println("BenchMarkTest.main()");
			//Thread.sleep(40000);
			
			for (int i = 0; i < 3; i++) {
				// ensuring mem is clean
				Runtime.getRuntime().gc();
				bench(new StringBenchmark(1000));
				bench(new StringBufferBenchmark(1000, 1));
				bench(new StringBufferBenchmark(1000, 1000));
				bench(new StringBuilderWithStringBenchmark(1000, 1));
				bench(new StringBuilderWithStringBenchmark(1000, 1000));
				bench(new StringBuilderWithCharBenchmark(1000, 1));
				bench(new StringBuilderWithCharBenchmark(1000, 1000));
				bench(new ThreadBenchmark(1000, 1, new CounterCallable(1000)));
				bench(new ThreadBenchmark(1000, 10, new CounterCallable(1000)));
				bench(new ThreadBenchmark(1000, 100, new CounterCallable(1000)));
				bench(new ThreadBenchmark(1000, 1000, new CounterCallable(1000)));
				//bench(new ThreadBenchmark(100, 1, new SleepBenchmark(100)));
				bench(new ThreadBenchmark(100, 10, new SleepCallable(100)));
				bench(new ThreadBenchmark(100, 100, new SleepCallable(100)));
				bench(new ThreadBenchmark(100, 1000, new SleepCallable(100)));
				System.out.println("==================================================================");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public static void bench(Benchmark<Object> benchmark) {
		benchmark.bench();
		System.out.println("BenchMarkTest.bench(0) :" + benchmark.getDescription()+" time="+benchmark.getDisplayTimeSpent()+" used memory="+benchmark.getMemoryConsumed()+" temp memory="+benchmark.getTempMemory());
//		benchmark.bench();
//		System.out.println("BenchMarkTest.bench(1) :" + benchmark.getDescription()+" time="+benchmark.getDisplayTimeSpent()+" used memory="+benchmark.getMemoryConsumed()+" temp memory="+benchmark.getTempMemory());
//		benchmark.bench();
//		System.out.println("BenchMarkTest.bench(2) :" + benchmark.getDescription()+" time="+benchmark.getDisplayTimeSpent()+" used memory="+benchmark.getMemoryConsumed()+" temp memory="+benchmark.getTempMemory());
	}

	public static String concat(String A, String B) {
		return new StringBuffer(A).append(B).toString();
	}
	
	public static boolean testInstance(Object a, Class<Object> b) {
		return a.getClass().isInstance(b);
	}

}
