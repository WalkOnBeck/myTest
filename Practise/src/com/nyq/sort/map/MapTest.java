package com.nyq.sort.map;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("aa","aa");
		map.get("aa");
		System.out.println(map.toString());
	}
}
