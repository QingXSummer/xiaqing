package com.java.lock;

public class ThreadStatus {

    public static void main(String[] args) {
        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){}
            }
        });
        t1.start();
        t1.interrupt();
        ThreadLocal<Integer> local = new ThreadLocal <>();
    }
}
