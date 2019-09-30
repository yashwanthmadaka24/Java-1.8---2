package com.mslc.training.qualcomm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadStateDemo {

	public static void main(String[] args) {

		MyMutableInteger i = new MyMutableInteger();

		MyBoundedDataStructure ds = new MyBoundedDataStructure(3);

		Thread t1 = new Thread() {

			@Override
			public void run() {

				ds.put("Value 1");

			}
		};

		Thread t2 = new Thread() {

			@Override
			public void run() {
				String value = ds.get(0);
				System.out.println("Value is : " + value);

			}
		};

		Thread t3 = new Thread() {

			@Override
			public void run() {
				String value = ds.get(0);
				System.out.println("Value is : " + value);

			}
		};

		t1.start();
		t2.start();
		t3.start();

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
							+ t2.getState().name() + " -- t3 is in : " + t3.getState().name());

				}
			};
		}.start();

		System.out.println("Main Thread Completed....");

	}

}

class MyBoundedDataStructure {

	private List<String> values = new ArrayList<String>();
	private int size;

	ReadWriteLock rwLock = new ReentrantReadWriteLock();
	Lock wLock = rwLock.writeLock();
	Lock rLock = rwLock.readLock();

	public MyBoundedDataStructure(int size) {
		this.size = size;
	}

	public String get(int index) {
		System.out.println(Thread.currentThread().getName() + " has entered get function");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.values.get(index);
	}

	public void put(String value) {
		System.out.println(Thread.currentThread().getName() + " has entered put function");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (values.size() == size) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		values.add(value);
	}

	public String take() {
		String value = values.remove(0);
		notify();
		return value;
	}

}

class MyMutableInteger {

	private int value;

	public void setValue(int value) {

		synchronized (this) {
			this.value = value;
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public int getValue() {

		synchronized (this) {
			return this.value;
		}

	}

}
