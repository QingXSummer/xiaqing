package com.java;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueTest {
	
	
	public static void main(String[] args) {
		
		Object object = new Object();
		
		List<Object> list = new ArrayList<>();
		
		for(int i=0 ;i<5;i++) {
			object = new Object();
			System.out.println(object);
			list.add(object);
		}
		
		for (Object object2 : list) {
			System.out.println(object2);
		}
		
	}
	
	
}
