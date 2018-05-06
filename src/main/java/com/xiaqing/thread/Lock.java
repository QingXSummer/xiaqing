package com.xiaqing.thread;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class Lock  {
	
	static int count = 10;
	
	static ReentrantLock lock = new ReentrantLock();	
	
	ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	ReadLock readLock = readWriteLock.readLock();
	WriteLock writeLock = readWriteLock.writeLock();
	
	public static void main(String[] args) {
		
		System.out.println(1 << 16 -1);
		
	}
	
	class T1Cunsumer extends Thread{
		@Override
		public void run() {
			for (;;) {
				try {
					readLock.lock();
					
				} finally {
					// TODO: handle finally clause
				}
			}

		}
	}
	
}
