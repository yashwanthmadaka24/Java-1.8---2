package com.mslc.training.qualcomm;

public class HitCounter {

	private int httpCounter = 0;
	private int httpsCounter = 0;
	
	private Object o1 = new Object();
	private Object o2 = new Object();
	
	
	
	
	public  void incrementHTTPCounter() {
		synchronized (o1) {
			httpCounter ++;	
		}
		
	}
	
	public   void incrementHTTPSCounter() {
		synchronized (o2) {
			
			httpsCounter++;	
		}
		
	}
	
	
	
	

}
