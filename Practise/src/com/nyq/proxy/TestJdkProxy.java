package com.nyq.proxy;

public class TestJdkProxy {
	public static void main(String[] args) {
		JdkProxyExample jdk = new JdkProxyExample();
		HelloWorld h = (HelloWorld)jdk.bind(new HelloWorldImpl());
		h.sayHelloWorld();
	}
		
}
