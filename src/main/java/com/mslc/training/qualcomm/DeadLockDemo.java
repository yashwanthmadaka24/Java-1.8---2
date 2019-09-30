package com.mslc.training.qualcomm;

public class DeadLockDemo {

	public static void main(String[] args) {

		Object o1 = new Object();
		Object o2 = new Object();

		Thread t1 = new Thread() {
			public void run() {
				
				synchronized (o1) {
					System.out.println("t1 has acquired the lock on o1");
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					synchronized (o2) {

						System.out.println("t1 has acquired the lock on o2");
					}
				}
			};
		};

		Thread t2 = new Thread() {
			public void run() {
				synchronized (o2) {
					System.out.println("t2 has acquired the lock on o2");

					synchronized (o1) {

						System.out.println("t2 has acquired the lock on o1");
					}
				}
			};
		};
		t1.start();
		t2.start();

	}
}
