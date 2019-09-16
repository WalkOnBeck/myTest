package com.design.day1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class UserEntity {
	private String userName;
	public UserEntity() {
		System.out.println("�����ʼ��������");
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// 1.��ʼ���Ĳ������޲ι��캯��
//		UserEntity userEntity = new UserEntity();
//		userEntity.userName = "������";
//		System.out.println(userEntity.userName);
		// 2.ʹ��java�ķ�����ƴ��������������·��
		Class<?> forName = Class.forName("com.design.day1.UserEntity");
		// 3.ʹ�÷�����ƴ�������
		UserEntity userEntity = (UserEntity)forName.newInstance();
		userEntity.userName = "111";
		System.out.println(userEntity.userName);
		
		// �����ȡ��ķ�����Ϣ
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
