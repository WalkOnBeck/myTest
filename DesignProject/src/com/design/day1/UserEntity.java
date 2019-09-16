package com.design.day1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class UserEntity {
	private String userName;
	public UserEntity() {
		System.out.println("对象初始化。。。");
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// 1.初始化的操作，无参构造函数
//		UserEntity userEntity = new UserEntity();
//		userEntity.userName = "啊哈哈";
//		System.out.println(userEntity.userName);
		// 2.使用java的反射机制创建对象类的完整路径
		Class<?> forName = Class.forName("com.design.day1.UserEntity");
		// 3.使用反射机制创建对象
		UserEntity userEntity = (UserEntity)forName.newInstance();
		userEntity.userName = "111";
		System.out.println(userEntity.userName);
		
		// 反射获取类的方法信息
//		Method[] methods = forName.getMethods();
//		for (Method method : methods) {
//			System.out.println(method.getName());
//		}
//		
		Field[] fileds = forName.getDeclaredFields();
		for (Field field : fileds) {
			System.out.println(field.getName());
		}
	}
}
