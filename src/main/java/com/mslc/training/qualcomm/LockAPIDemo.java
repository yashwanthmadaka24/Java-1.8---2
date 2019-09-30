package com.mslc.training.qualcomm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockAPIDemo {

	public synchronized void f1() {
		System.out.println(Thread.currentThread().getName() + " has acquired the lock in f1");
		f2();
	}

	public synchronized void f2() {

		System.out.println(Thread.currentThread().getName() + " has acquired the lock in f2");
	}

	public static void main(String[] args) {

		LockAPIDemo d = new LockAPIDemo();

		new Thread() {
			{
				setName("T0-Thread");
			}

			public void run() {

				d.f1();
			};
		}.start();

		if (true) {
			return;
		}

		MyBoundedDataStructure1 ds = new MyBoundedDataStructure1(3);
		ds.put("Value 1");
		ds.put("Value 2");
		ds.put("Value 3");

		Thread t1 = new Thread() {

			@Override
			public void run() {

				ds.put("Value 4");
				// i.setValue(50);

			}
		};

		t1.start();

		Thread t2 = new Thread() {

			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String value = ds.take();
				System.out.println("Value is : " + value);

			}
		};

		t2.start();

		new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println("t1 is in : " + t1.getState().name() + " state" + " --  t2 is in : "
							+ t2.getState().name());

				}
			};
		}.start();

		System.out.println("Main Thread Completed....");

	}
}

class MyBoundedDataStructure1 {

	private List<String> values = new ArrayList<String>();
	private int size;
	Lock lock = new ReentrantLock();
	Condition fullCondition = lock.newCondition();

	public MyBoundedDataStructure1(int size) {
		this.size = size;
	}

	public void put(String value) {
		try {
			lock.lock();

//			lock.lockInterruptibly();
			while (values.size() == size) {
				try {
					fullCondition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			values.add(value);
		} finally {
			lock.unlock();
		}
	}

	public String take() {
		try {
			lock.lock();
			String value = values.remove(0);
//			notify();
			fullCondition.signal();
			return value;

		} finally {
			lock.unlock();
		}
	}

}
