package com.mslc.training.qualcomm;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	
	
	public static void main(String[] args) {
		
		Random r = new Random();
		CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {

			@Override
			public void run() {
				
				System.out.println(" ************ Barrier is reset ******* ");
				
			}
			
		});
		
		for (int i = 0; i < 3; i++) {
			
			new Thread() {
				public void run() {
					
					System.out.println(" -------- Phase 1 --------- ");
					long sleep = r.nextInt(5000);
					System.out.println(Thread.currentThread().getName() + " will arrive in  " + sleep);
					
					try {
						Thread.sleep(sleep);
						cb.await();
						System.out.println(Thread.currentThread() + " has arrived in phase 1");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println(" -------- Phase 2 --------- ");
					
					long sleep2 = r.nextInt(5000);
					System.out.println(Thread.currentThread().getName() + " will arrive in  " + sleep2);
					
					try {
						Thread.sleep(sleep2);
						cb.await();
						System.out.println(Thread.currentThread() + " has arrived in phase 2");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				};
			}.start();
		}
		
		
	}

}
