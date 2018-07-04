package com.java.basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import test.test.App;

public class GenericType {
	public static void main(String[] args) {
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		
//		String data=dateFormat.format(new Date());
//		System.out.println(data);
		
		File file = new File("C:\\Users\\QingX\\eclipse-workspace\\tomcat\\target\\classes\\boxman.jpg");
		System.out.println();
		
		
	}
	
	public static <T> void say(T t) {
		System.out.println(t);
	}
}




class Plate<T> {
	private T item;
	public Plate(T t){item=t;}
	public void set(T t){item=t;}
	public T get(){return item;}
}



class Fruit{}

class Apple extends Fruit{}

class RedApple extends Apple{}

class Banana extends Fruit{}

class YelloBanana extends Banana{}