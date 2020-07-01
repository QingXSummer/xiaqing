package com.java.lock;

import java.util.Date;
import java.util.HashMap;
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
        HashMap hashMap;
//		System.out.println(1 << 30);
		System.out.println(new Date(1370907000000L).toLocaleString());
		System.out.println(new Date(1370907016000L).toLocaleString());
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
