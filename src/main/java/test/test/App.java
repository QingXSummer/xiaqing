package test.test;

import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public static CountDownLatch latch = new CountDownLatch(10);
	public static int count=0;
    public static void main( String[] args )
    {
        for(int i = 0; i<10; i++) {
        	new Thread(new Runnable() {				
				@Override
				public void run() {
					latch.countDown();
					for(int j = 0; j<1000; j++) {
						count++;
					}
				}
			}).start();
        }
        
        try {
			Thread.sleep(1000 * 5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println(count);
    }
}
