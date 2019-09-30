package com.mslc.training.qualcomm;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {

		CountDownLatch latch = new CountDownLatch(4);

		ExecutorService service = Executors.newFixedThreadPool(4);
		Random r = new Random();
		for (int i = 0; i < 4; i++) {
			service.submit(new Runnable() {

				@Override
				public void run() {

					try {
						long sleep = r.nextInt(5000);
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Thread : " + Thread.currentThread().getName() + " - has arrived");
					latch.countDown();

				}

			});
		}

		latch.await();
		System.out.println("Updating database...");
		service.shutdown();

	}

}
