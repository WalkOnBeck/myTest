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
		GregorianCalendar calendar = new GregorianCalendar(year, month, 1);//灵活的输入年份，月份，日期，
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//定义日期显示格式
        List<Element> elements=new ArrayList<Element>();
		try {
	        // 上个月显示的日期
	        calendar.add(Calendar.MONTH, -2);//获取上个月月份
	        String lastMonth = sdf.format(calendar.getTime());//上个月
	        SimpleCalendar s = new SimpleCalendar();
	        long lastMonthLenth = s.solarDays(Integer.valueOf(lastMonth.substring(0 , 4)), Integer.valueOf(lastMonth.substring(5)) - 1);// 上个月的天数
	        long lastCount = this.getCountDay(param + "-01");// 上个月显示几天
	        for(long i = lastMonthLenth-lastCount + 1; i <= lastMonthLenth; i++) {
	        	StringBuffer day = new StringBuffer();
	        	day.append(lastMonth).append("-").append(i);
	        	Element element = SimpleCalendar.getCalendarDetail(day.toString(), "yyyy-MM-dd");
				elements.add(element);
	        }
	        
	        // 当前月显示的日期
	        long monthLenth = s.solarDays(year, month-1);// 当前月几天
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
	        
	        // 下个月显示的日期
	        calendar.add(Calendar.MONTH, + 2);//获取上个月月份
	        String nextMonth = sdf.format(calendar.getTime());//下个月
	        long nextCount = 7 - this.getCountDay(param + "-" + monthLenth) - 1;// 下个月显示几天
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
		int lastNum = 0;// 上个月显示天数
		SimpleCalendar.Element element = null;
		try {
			element = SimpleCalendar.getCalendarDetail(date, "yyyy-MM-dd");
		} catch (Exception e) {
			e.printStackTrace();
		}
		char[] nStr1 = new char[]{'一', '二', '三', '四', '五', '六', '日'};
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
