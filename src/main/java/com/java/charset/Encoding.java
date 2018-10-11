package com.java.charset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class Encoding {
	
	
	public static void main(String[] args) throws IOException {
		String  msg = "hello0xC40xE3";
		System.out.println(Charset.defaultCharset().toString());
		System.out.println(msg.getBytes("GBK").length);
		
		File file1 = new File("C:\\tmp\\new1.txt");
		InputStream inputStream = new FileInputStream(file1);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
		String txt = reader.readLine();
		System.out.println(txt.getBytes("GBK").length);
		System.out.println(txt);
		System.out.println(new String(txt.getBytes("GBK"),"UTF8"));
		System.out.println(TestEncoder.getUTF8StringFromGBKString(txt));
	}
	
	
	
}
