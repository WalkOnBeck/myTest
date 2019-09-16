package com.itmayiedu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping("/index")
	public String index() {
		return "1111sadf";
	}
	
	@RequestMapping(value = { "/file/download" }, method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@RequestParam("filename") String fileName) throws IOException {
		String property = System.getProperty("user.dir");
		File file3 = new File(property);
		String filePath = file3.getParent() + File.separator + "upload" + File.separator + fileName;
		@SuppressWarnings("resource")
		InputStream in = new FileInputStream(new File(filePath));// 将该文件加入到输入流之中
		byte[] body = null;
		body = new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
		in.read(body);// 读入到输入流里面
 
		fileName = new String(fileName.getBytes("gbk"), "iso8859-1");// 防止中文乱码
		HttpHeaders headers = new HttpHeaders();// 设置响应头
		headers.add("Content-Disposition", "attachment;filename=" + fileName);
		HttpStatus statusCode = HttpStatus.OK;// 设置响应吗
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}
	
}
