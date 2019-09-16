package com.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class Gbk2utf8 {
	public static void main(String[] args) throws InterruptedException, IOException {
			Gbk2utf8 tt = new Gbk2utf8();
	      tt.copyAll("D:\\test", "E:\\java");
	   }
	    
	   public void copyAll(String dir,String des) throws IOException{
	       
	      File parent = new File(dir);
	      System.err.println(parent.getAbsolutePath());
	      String[] allFile = parent.list();
	       
	      for (String string : allFile) {
	         File file = new File(dir+"/"+string);
	         if(file.isFile()){
	            this.copy(file,des);
	         }else{
	            File newDes = new File(des+"/"+string);
	            if(!newDes.exists()) newDes.mkdir();
	            this.copyAll(dir+"/"+string,des+"/"+string);
	         }
	      }
	   }
	    
	   public void copy(File file,String des) throws IOException{
	      BufferedReader br = new BufferedReader(new InputStreamReader(
	            new FileInputStream(file), Charset.forName("gbk")));
	      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
	            new FileOutputStream(des+"/"+file.getName()), Charset.forName("UTF-8")));
	      String lineTxt = null;
	      while ((lineTxt = br.readLine()) != null) {
	         bw.write(lineTxt);
	         bw.newLine();
	      }
	      bw.flush();
	      bw.close();
	      br.close();
	   }
}
