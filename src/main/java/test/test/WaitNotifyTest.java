package test.test;

import java.util.concurrent.TimeUnit;

public class WaitNotifyTest {
	
	public static Object object = new Object();
	
	public static WaitNotifyObject waitNotifyObject = new WaitNotifyObject();
		
	public static void main(String[] args) throws InterruptedException {
//		new Thread(new Wait()).start();
//		Thread.sleep(500);
//		new Thread(new Notify()).start();
		
		synchronized (object) {
			System.out.println("--");
		}
		
	}
	
	static class Wait implements Runnable{
		@Override
		public void run() {
			waitNotifyObject.doWait();
		}
	}
	
	static class Notify implements Runnable{
		@Override
		public void run() {
			waitNotifyObject.doNotify();
		}
	}
	static class WaitNotifyObject{
		
		public void doWait() {
			synchronized (object) {
				try {
					object.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("收到通知");
		}
		
		public void doNotify() {
			synchronized (object) {
				object.notify();
			}
			System.out.println("发起通知");
		}
		
	}
	
}
