package com.mslc.training.qualcomm;

public class ServiceLocator {
	
	
	static {
		System.out.println("ServiceLocator class is loaded...");
	}
	
	private ServiceLocator() {
	
		System.out.println("ServiceLocator Instantiated.....");
	}

	
	private static class SingletonHolder { 
		static {
			System.out.println("SingletonHolder is loaded....");
		}
		private static ServiceLocator me = new ServiceLocator();
	}
	
	public  static ServiceLocator getInsance() {
		
		return SingletonHolder.me;
	}
	public static void yababdabadoo() {
		
	}
	
	

}
