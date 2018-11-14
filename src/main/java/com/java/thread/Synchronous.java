package com.java.thread;

public class Synchronous {
	
	private static Object object = new Object();
	
	
	public static void main(String[] args) throws InterruptedException {
		object.notifyAll();
		synchronized (object) {
			object.wait();			
		}
	}
	
}
