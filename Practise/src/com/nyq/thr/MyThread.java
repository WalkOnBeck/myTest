package com.nyq.thr;

public class MyThread extends Thread {
	
	public void run() {
		System.err.println(MyObject.getInstance().hashCode());
	}
}
