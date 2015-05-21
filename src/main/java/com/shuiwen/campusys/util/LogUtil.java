package com.shuiwen.campusys.util;


public class LogUtil {
	

	public static void AuthTitlLog(String Auth, String title, String log){
		System.out.println(Auth+"~"+title+":"+log);
	}
	
	public static void TitlLog(String title, String log){
		System.out.println(title+":"+log);
	}
	
	
}
