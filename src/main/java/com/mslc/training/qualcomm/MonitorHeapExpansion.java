package com.mslc.training.qualcomm;

import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc -XX:+PrintGCDetails -Xloggc:gc.log -Xms100M -Xmx1G
 * -XX:+UseConcMarkSweepGC <br>
 * <br>
 * This class goes on adding new objects into any array after every 2 seconds.
 * This will result in memory expansion as monitored using a thread that prints
 * memory status constantly. <br>
 * <br>
 * At first run this app using -Xms10M -Xmx1G as the VM argument (Specifying
 * -Xms1M will not start the JVM saying insufficient memory to start JVM; what
 * else to expect ? :) )
 * 
 * 
 * To get the defaults : java -XX:+PrintCommandLineFlags -version
 * 
 * @author MuhammedShakir
 * 
 */
public class MonitorHeapExpansion {

	private static int allocateMemory = 0;

	public static void main(String[] args) throws InterruptedException {

		System.out.println(Runtime.getRuntime().availableProcessors());

		if (args.length == 0) {
			allocateMemory = 5;
		} else {
			allocateMemory = Integer.parseInt(args[0]);
		}

		System.out.println("Will allocate : " + allocateMemory + " each time in the loop");
		System.out.println("****** Monitoring Thread will start in 1 second ****");
		Thread.sleep(1000);

		new Thread() {

			{
				setName("My Monitoring Thread");
			}

			public void run() {

				while (true) {

					System.out.println(" Free memory : " + (Runtime.getRuntime().freeMemory() / 1024) / 1024
							+ " Total Memory : " + (Runtime.getRuntime().totalMemory() / 1024) / 1024 + " Max Memory : "
							+ (Runtime.getRuntime().maxMemory() / 1024) / 1024);

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}.start();

		Thread t2 = new Thread() {

			private List<byte[]> byteArrayBuffer = new ArrayList<byte[]>();

			{
				setName("My DataBuilder Thread");
			}

			public void run() {

				try {

					int counter = 0;

					while (true) {

						int allocateNow = (allocateMemory * (1024 * 1024));
						byteArrayBuffer.add(new byte[allocateNow]);

						if (counter >= 15) {
							byteArrayBuffer = new ArrayList<byte[]>();
							// System.out.println("Released memory");
							Thread.sleep(1000);
							counter = 0;
						}
						counter++;
					}

				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				} catch (OutOfMemoryError error) {

					System.out.println("***************************************** OOME : " + error.getMessage());
					System.exit(0);
				}

			}

		};
		t2.start();

		System.out.println("Debug....");

	}
}
