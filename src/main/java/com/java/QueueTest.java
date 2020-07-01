package com.java;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueTest {
	
	
	public static void main(String[] args) {

        int numCores = Runtime.getRuntime().availableProcessors();
        System.out.println(numCores);
        int slect  = Math.max((int) Math.sqrt((float) numCores/2), 1);
		System.out.println(slect);
	}
	
	
}
