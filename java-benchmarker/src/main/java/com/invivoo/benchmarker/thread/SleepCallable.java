package com.invivoo.benchmarker.thread;

import java.util.concurrent.Callable;

public final class SleepCallable implements Callable<Object> {
	private final long sleepTime;

	public SleepCallable(long sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	public Object call() throws Exception {
		Thread.sleep(sleepTime);
		return null;
	}

	@Override
	public String toString() {
		return "Sleep("+sleepTime+")";
	}
}