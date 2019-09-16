package com.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.util.SimpleCalendar.Element;

public class Test {
	public String getDate(String param) {
		int year = Integer.valueOf(param.substring(0 , 4));
		int month = Integer.valueOf(param.substring(5));
		GregorianCalendar calendar = new GregorianCalendar(year, month, 1);//����������ݣ��·ݣ����ڣ�
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//����������ʾ��ʽ
        List<Element> elements=new ArrayList<Element>();
		try {
	        // �ϸ�����ʾ������
	        calendar.add(Calendar.MONTH, -2);//��ȡ�ϸ����·�
	        String lastMonth = sdf.format(calendar.getTime());//�ϸ���
	        SimpleCalendar s = new SimpleCalendar();
	        long lastMonthLenth = s.solarDays(Integer.valueOf(lastMonth.substring(0 , 4)), Integer.valueOf(lastMonth.substring(5)) - 1);// �ϸ��µ�����
	        long lastCount = this.getCountDay(param + "-01");// �ϸ�����ʾ����
	        for(long i = lastMonthLenth-lastCount + 1; i <= lastMonthLenth; i++) {
	        	StringBuffer day = new StringBuffer();
	        	day.append(lastMonth).append("-").append(i);
	        	Element element = SimpleCalendar.getCalendarDetail(day.toString(), "yyyy-MM-dd");
				elements.add(element);
	        }
	        
	        // ��ǰ����ʾ������
	        long monthLenth = s.solarDays(year, month-1);// ��ǰ�¼���
	        for(long i = 1; i <= monthLenth; i++) {
	        	StringBuffer day = new StringBuffer();
				if (i < 10) {
					day.append(param).append("-0").append(i);
				} else {
					day.append(param).append("-").append(i);
				}
				Element element = SimpleCalendar.getCalendarDetail(day.toString(), "yyyy-MM-dd");
				elements.add(element);
	        }
	        
	        // �¸�����ʾ������
	        calendar.add(Calendar.MONTH, + 2);//��ȡ�ϸ����·�
	        String nextMonth = sdf.format(calendar.getTime());//�¸���
	        long nextCount = 7 - this.getCountDay(param + "-" + monthLenth) - 1;// �¸�����ʾ����
	        for(long i = 1; i <= nextCount; i++) {
	        	StringBuffer day = new StringBuffer();
	        	day.append(nextMonth).append("-0").append(i);
	        	Element element = SimpleCalendar.getCalendarDetail(day.toString(), "yyyy-MM-dd");
				elements.add(element);
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elements.toString();
	}
	
	public int getCountDay(String date) {
		int lastNum = 0;// �ϸ�����ʾ����
		SimpleCalendar.Element element = null;
		try {
			element = SimpleCalendar.getCalendarDetail(date, "yyyy-MM-dd");
		} catch (Exception e) {
			e.printStackTrace();
		}
		char[] nStr1 = new char[]{'һ', '��', '��', '��', '��', '��', '��'};
		for (int i = 0; i < 7; i++) {
			if (element.getWeek()==nStr1[i]) {
				lastNum = i;
			}
		}
		return lastNum;
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		System.out.println(t.getDate("2019-01"));
	}
	
}
