package com.java.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {
	
	static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(100);
	
	public static void main(String[] args) {
		
		Thread.yield();
		
		System.out.println(Thread.activeCount());
		
		System.out.println(null instanceof Object);
		
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 60, TimeUnit.MINUTES, queue);
//		
//		System.out.println( Runtime.getRuntime().availableProcessors());
		
//		System.out.println(executor.getActiveCount());
//		executor.execute(null);
		
//		System.out.println(1 << 0x1d);
//		
//		
//		Future<Integer> count =executor.submit(new Callable<Integer>() {
//
//			@Override
//			public Integer call() throws Exception {
//				// TODO Auto-generated method stub
//				System.out.println("-----------");
//				Thread.sleep(1000*5);
//				return 1;
//			}
//			
//		});
//		try {
//			System.out.println(count.get());
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	
}
