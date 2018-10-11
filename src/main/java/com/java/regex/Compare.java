package com.java.regex;

import java.util.ArrayList;
import java.util.List;

public class Compare {
	
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		for(int i =0 ;i<10000;i++) {
//			System.out.println(1);
		}
		long t2 =System.currentTimeMillis();
		
		System.out.println(t2-t1);
	}
}
