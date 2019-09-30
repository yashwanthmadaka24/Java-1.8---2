package com.mslc.training.qualcomm;

public class JoinDemo {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		
		Thread t1  = new Thread() {
			
			public void run() {
				
				System.out.println("T1 started....");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			};
		};
		
		t1.start();

		Thread mainThread = Thread.currentThread();
		new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(mainThread.getState().name());
				}
				
			};
		}.start();
		
		
		t1.join();
		
		
		System.out.println("After t1 is completed....");
		
		
		
		
		
		
		
	} 
	
	

}
