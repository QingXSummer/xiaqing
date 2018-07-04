package com.java.regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailSpider {
	
	static Pattern pattern = Pattern.compile(".+(?=\\s)");
	
	static File file = new File("C:\\Users\\QingX\\Desktop\\test.txt");
	
	public static void main(String[] args) throws IOException {
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String tmp;
		while((tmp=bufferedReader.readLine())!=null) {
//			Matcher matcher = pattern.matcher(tmp);
//			while(matcher.find()) {
//				System.out.println(matcher.group());
//			}
			String[] infos = tmp.split("\\s");
			for (String info : infos) {
				System.out.println(info);
			}
			
		}
		bufferedReader.close();
	}
	
}
