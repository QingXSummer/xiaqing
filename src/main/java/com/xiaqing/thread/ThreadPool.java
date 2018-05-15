package com.xiaqing.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
	
	static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(100);
	
	public static void main(String[] args) {
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 60, TimeUnit.MINUTES, queue);
		
//		System.out.println( Runtime.getRuntime().availableProcessors());
		
//		System.out.println(executor.getActiveCount());
//		executor.execute(null);
		
		System.out.println(1 << 0x1d);
		
	}
	
}
