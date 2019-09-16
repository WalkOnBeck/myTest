package com.itmayiedu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tomcat.util.codec.binary.Base64;

public class BaseImageTest {
	/**
     * 图片转为Base64字节码
     * @param path 图片路径
     * @return 返回base64字节码
     */
  public static String imageToBase64(String path) {
      byte[] data = null;
      try {
          InputStream in = new FileInputStream(path);
          data = new byte[in.available()];
          in.read(data);
          in.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
      Base64 base64 = new Base64();
      return base64.encodeToString(data);
  }
  
  /**
   * Base64字节码转图片
   * @param base64Str 字节码存储路径
   * @param path 文件存储路径
   * @return 返回true或者false
   */
  public static boolean base64ToImage(String base64Str, String path) {
      if (base64Str == null){
          return false;
      }
      Base64 base64 = new Base64();
      try {
          byte[] bytes = base64.decodeBase64(base64Str);
          for (int i = 0; i < bytes.length; ++i) {
              if (bytes[i] < 0) {
                  bytes[i] += 256;
              }
          }
          File img = new File(path);
          if (!img.getParentFile().exists()) {
              img.getParentFile().mkdirs();
          }
          OutputStream out = new FileOutputStream(path);
          out.write(bytes);
          out.flush();
          out.close();
          return true;
      } catch (Exception e) {
          return false;
      }
  }

	public static void main(String[] args) {
		BaseImageTest t = new BaseImageTest();
		String base64Str="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";
		boolean a = t.base64ToImage(base64Str, "D://i.png");
		System.out.println(a);
//		String b = t.imageToBase64("D://pic.png");
//		System.out.println(b);
	}
}