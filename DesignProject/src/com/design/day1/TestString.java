package com.design.day1;

public class TestString {
	public static void main(String[] args) {
		String str6 = "b";  
        String str7 = "a" + str6;  
        String str67 = "ab";  
        System.out.println("str7 = str67 : "+ (str7 == str67));  
        //��str6Ϊ�������������ڲŻᱻ������  
        final String str8 = "b";  
        String str9 = "a" + str8;  
        String str89 = "ab";  
        System.out.println("str9 = str89 : "+ (str9 == str89));  
        //��str8Ϊ���������������ڻᱻ�Ż�  
	}
}
