package com.mslc.training.qualcomm;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Semaphore s = new Semaphore(5);
		
		s.acquire(5);
		System.out.println("5 acquires done");
		
		new Thread() {
			public void run() {
				try {
					Thread.sleep(Long.MAX_VALUE);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				s.release(50);
			};
		}.start();
		
		s.acquire();
		
		
		System.out.println("After 6th : "  + s.availablePermits());
		
		
	}

}
