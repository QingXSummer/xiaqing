package test.test;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupted {
	
	public static void main(String[] args) throws InterruptedException {
		Thread busy = new Thread(new BusyThread());
		Thread block = new Thread(new BlockThread());
		
		busy.start();
		block.start();
		
		Thread.sleep(1000);
		
		busy.interrupt();
		block.interrupt();
		
		System.out.println("busy------isInterrupted="+busy.isInterrupted());
		System.out.println("block------isInterrupted="+block.isInterrupted());
		
	}
	
	static class BusyThread implements Runnable{
		@Override
		public void run() {
			while (true) {
				
			}
		}		
	}
	
	static class BlockThread implements Runnable{
		@Override
		public void run() {
			try {
				TimeUnit.MINUTES.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
}
