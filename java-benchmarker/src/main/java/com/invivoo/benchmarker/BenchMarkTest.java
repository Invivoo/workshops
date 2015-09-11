package com.invivoo.benchmarker;

import com.invivoo.benchmarker.search.MapSearchBenchmark;
import com.invivoo.benchmarker.search.SimpleListSearchBenchmark;
import com.invivoo.benchmarker.search.SortedListSearchBenchmark;
import com.invivoo.benchmarker.string.StringBenchmark;
import com.invivoo.benchmarker.string.StringBufferBenchmark;
import com.invivoo.benchmarker.string.StringBuilderWithCharBenchmark;
import com.invivoo.benchmarker.string.StringBuilderWithStringBenchmark;
import com.invivoo.benchmarker.thread.CounterCallable;
import com.invivoo.benchmarker.thread.SleepCallable;
import com.invivoo.benchmarker.thread.ThreadBenchmark;

//-agentlib:hprof=heap=sites,net=localhost:6060,doe=n
public class BenchMarkTest {
	public static void main(String[] args) {
		//Runtime.getRuntime().traceInstructions(true);
		//Runtime.getRuntime().traceMethodCalls(true);

		try {
			System.out.println("BenchMarkTest.main()");
			//Thread.sleep(40000);
			
			for (int i = 0; i < 3; i++) {
				// ensuring mem is clean
				Runtime.getRuntime().gc();
				benchString();
				benchThread();
				benchSearch();
				System.out.println("==================================================================");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	private static void benchString() {
		bench(new StringBenchmark(1000));
		bench(new StringBufferBenchmark(1000, 1));
		bench(new StringBufferBenchmark(1000, 1000));
		bench(new StringBuilderWithStringBenchmark(1000, 1));
		bench(new StringBuilderWithStringBenchmark(1000, 1000));
		bench(new StringBuilderWithCharBenchmark(1000, 1));
		bench(new StringBuilderWithCharBenchmark(1000, 1000));
	}

	private static void benchThread() {
		bench(new ThreadBenchmark(1000, 1, new CounterCallable(1000)));
		bench(new ThreadBenchmark(1000, 10, new CounterCallable(1000)));
		bench(new ThreadBenchmark(1000, 100, new CounterCallable(1000)));
		bench(new ThreadBenchmark(1000, 1000, new CounterCallable(1000)));
		//bench(new ThreadBenchmark(100, 1, new SleepCallable(100)));
		bench(new ThreadBenchmark(100, 10, new SleepCallable(100)));
		bench(new ThreadBenchmark(100, 100, new SleepCallable(100)));
		bench(new ThreadBenchmark(100, 1000, new SleepCallable(100)));
	}

	private static void benchSearch() {
		Comparable [] content = new Integer[10000];
		for (int i = 0; i < content.length; i++) {
			// just to ensure this is not sorted for the simple list
			content[i] = (i * 11) % content.length;
		}
		Integer unknown = new Integer(-1);
		bench(new SimpleListSearchBenchmark(content, unknown));
		bench(new SortedListSearchBenchmark(content, unknown));
		bench(new MapSearchBenchmark(content, unknown));
		bench(new SimpleListSearchBenchmark(unknown), content);
		bench(new SortedListSearchBenchmark(unknown), content);
		bench(new MapSearchBenchmark(unknown), content);
	}

	public static void bench(Benchmark<Object> benchmark) {
		bench(benchmark, null);
	}
	
	public static void bench(Benchmark<Object> benchmark, Object param) {
		benchmark.bench(param);
		System.out.println("BenchMarkTest.bench(0) :" + benchmark.getDescription()+" time="+benchmark.getDisplayTimeSpent()+" used memory="+benchmark.getMemoryConsumed()+" temp memory="+benchmark.getTempMemory());
//		benchmark.bench();
//		System.out.println("BenchMarkTest.bench(1) :" + benchmark.getDescription()+" time="+benchmark.getDisplayTimeSpent()+" used memory="+benchmark.getMemoryConsumed()+" temp memory="+benchmark.getTempMemory());
//		benchmark.bench();
//		System.out.println("BenchMarkTest.bench(2) :" + benchmark.getDescription()+" time="+benchmark.getDisplayTimeSpent()+" used memory="+benchmark.getMemoryConsumed()+" temp memory="+benchmark.getTempMemory());
	}
}
