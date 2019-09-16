package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Qeww {
	public static void main(String[] args) {
//		GregorianCalendar calendar = new GregorianCalendar(2015, 12, 1);//����������ݣ��·ݣ����ڣ�
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//����������ʾ��ʽ
//        calendar.add(Calendar.MONTH, -2);//��ȡ�ϸ����·�
//        System.out.println(sdf.format(calendar.getTime()));//������
//        System.out.println(sdf.format(calendar.getTime()));//��ӡ��ǰ�·ݵ���һ���·�
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			cal.setTime(sdf.parse("2015-09"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.MONTH, -1);
		System.out.println(sdf.format(cal.getTime()));
		cal.add(Calendar.MONTH, +2);
		System.out.println(sdf.format(cal.getTime()));
	}
}
