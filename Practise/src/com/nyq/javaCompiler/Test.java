package com.nyq.javaCompiler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class Test {
	public static void main(String[] args) {
		JavaCompiler compoler = ToolProvider.getSystemJavaCompiler();
		int result = compoler.run(null, null, null, "D:\\myJavaCompoler/HelloWorld.java");
		System.err.println(result==0?"编译成功":"编译失败");
		
		
		
		String str="public class HelloWorld{\n" +
                "\tpublic static void main(String[] args){\n" +
                "\t\tSystem.out.println(\"helloworld\");\n" +
                "\t}\n" +
                "}";
	}
}
