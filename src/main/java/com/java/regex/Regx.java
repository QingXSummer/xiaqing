package com.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regx {

	public static String msg1 ="ods_m_svc_pay_plan_20045";
	public static String msg2 ="stg_d_svc_ent_10501_201807";
	public static String msg3 ="ods_m_svc_pay_plan_20045_20180901";
	

	
	public static void main(String[] args) {
		
		String reg = "^((stg|ods|dwi|dwv|dm)_(m|d|y)_(svc)_(\\w+?))_?(20[1-5][0-9][0-1][0-9]([0-3][0-9])?)?$";
		
		Pattern pattern = Pattern.compile(reg); 
		
		Matcher matcher = pattern.matcher(msg2);
		
	
		
		
		if (matcher.matches()) {
			System.out.println(matcher.group(0));
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));			
			System.out.println(matcher.group(3));			
			System.out.println(matcher.group(4));			
			System.out.println(matcher.group(5));				
			System.out.println(matcher.group(6));				
		}
		
		
	}
	
}
