package com.xiaqing.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class CountDownLatchTest {
	
	static CountDownLatch latch = new CountDownLatch(3);
	
	static CyclicBarrier barrier = new CyclicBarrier(2);
	
	static Semaphore semaphore = new Semaphore(2);
	
	public static void main(String[] args) {
		new Task1().start();
		
		new Task2().start();
		
		
	}
	
	
	static class Task1 extends Thread{
		@Override
		public void run() {
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(1);
		}
	}
	
	static class Task2 extends Thread{
		@Override
		public void run() {
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(2);
		}
	}
	
} 

