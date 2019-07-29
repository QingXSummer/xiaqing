package com.java.charset;


public class Encoding {




	public static void main(String[] args)  {

	   final int count = 0;
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                int temp =count;
               for (int i=0;i<10;i++){
                   temp++;
                   System.out.println(Thread.currentThread().getName()+"---"+temp);
               }
            }
        });
        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                int temp =count;
                for (int i=0;i<10;i++){
                    temp++;
                    System.out.println(Thread.currentThread().getName()+"---"+temp);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int temp =count;
                for (int i=0;i<10;i++){
                    temp++;
                    System.out.println(Thread.currentThread().getName()+"---"+temp);
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();

    }

	
}
