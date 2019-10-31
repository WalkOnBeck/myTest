package com.nyq.sort;

public class RunTest {
	public static void main(String[] args) {
//		StringBuffer sb = new StringBuffer("Hello");
//		change(sb);
//		System.out.println(sb);
		System.out.print("最大内存");
		System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");
		System.out.print("可用内存");
		System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");
		System.out.print("已经使用内存");
		System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");

	}
	
	public static void change(StringBuffer strBuf) {
		strBuf = new StringBuffer("Hi");
		strBuf.append("World");
	}
}
