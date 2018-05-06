package com.java.refrence;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class BasicRef {
	
	public static void main(String[] args) {
		WeakReference<Object> wr = new WeakReference<Object>(new Object());
		SoftReference<Object> sr = new SoftReference<Object>(new Object());
        System.out.println(wr.get());
        System.out.println(sr.get());
        System.gc();                //通知JVM的gc进行垃圾回收
        System.out.println(wr.get());
        System.out.println(sr.get());
	}
	
}
