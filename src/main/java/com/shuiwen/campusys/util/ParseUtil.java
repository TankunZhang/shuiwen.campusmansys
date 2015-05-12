package com.shuiwen.campusys.util;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.shuiwen.campusys.bean.CacheKecheng;
import com.shuiwen.campusys.bean.XueSheng;

public class ParseUtil {
	
	private static Gson gson = new Gson();

	public static String XueShengformToJson(byte[] byteArray){
		String formStr = ParseString(byteArray);
		JSONArray formArray = JSON.parseArray(formStr);
		String realJson = "{";
		for(int i=0;i<formArray.size();i++){
			if(i!=(formArray.size()-1)){
				JSONObject jb = (JSONObject) formArray.get(i);
				realJson = realJson+"\""+jb.getString("name")+"\":";
				if(jb.getString("name").equals("xiaoquid")||jb.getString("name").equals("id"))
					realJson = realJson+jb.getString("value")+",";
				else
					realJson = realJson+"\""+jb.getString("value")+"\",";
			}else if(i==(formArray.size()-1)){
				JSONObject jb = (JSONObject) formArray.get(i);
				realJson = realJson+"\""+jb.getString("name")+"\":";
				if(jb.getString("name").equals("xiaoquid")||jb.getString("name").equals("id"))
					realJson = realJson+jb.getString("value")+"}";
				else
					realJson = realJson+"\""+jb.getString("value")+"\"}";
			}
		}
		
		return realJson;
	}
	
	public static String GuanliyuanformToJson(byte[] byteArray){
		String formStr = ParseString(byteArray);
		JSONArray formArray = JSON.parseArray(formStr);
		String realJson = "{";
		for(int i=0;i<formArray.size();i++){
			if(i!=(formArray.size()-1)){
				JSONObject jb = (JSONObject) formArray.get(i);
				realJson = realJson+"\""+jb.getString("name")+"\":";
				if(jb.getString("name").equals("quanxian"))
					realJson = realJson+jb.getString("value")+",";
				else
					realJson = realJson+"\""+jb.getString("value")+"\",";
			}else if(i==(formArray.size()-1)){
				JSONObject jb = (JSONObject) formArray.get(i);
				realJson = realJson+"\""+jb.getString("name")+"\":";
				if(jb.getString("name").equals("quanxian"))
					realJson = realJson+jb.getString("value")+"}";
				else
					realJson = realJson+"\""+jb.getString("value")+"\"}";
			}
		}
		
		return realJson;
	}
	
	public static String XiaoquformToJson(byte[] byteArray){
		String formStr = ParseString(byteArray);
		JSONArray formArray = JSON.parseArray(formStr);
		String realJson = "{";
		for(int i=0;i<formArray.size();i++){
			if(i!=(formArray.size()-1)){
				JSONObject jb = (JSONObject) formArray.get(i);
				realJson = realJson+"\""+jb.getString("name")+"\":";
				realJson = realJson+"\""+jb.getString("value")+"\",";
			}else if(i==(formArray.size()-1)){
				JSONObject jb = (JSONObject) formArray.get(i);
				realJson = realJson+"\""+jb.getString("name")+"\":";
				realJson = realJson+"\""+jb.getString("value")+"\"}";
			}
		}
		
		return realJson;
	}
	
	public static String BanjiformToJson(byte[] byteArray){
		String formStr = ParseString(byteArray);
		JSONArray formArray = JSON.parseArray(formStr);
		String realJson = "{";
		for(int i=0;i<formArray.size();i++){
			if(i!=(formArray.size()-1)){
				JSONObject jb = (JSONObject) formArray.get(i);
				realJson = realJson+"\""+jb.getString("name")+"\":";
				if(jb.getString("name").equals("kemuid")||jb.getString("name").equals("id")||jb.getString("name").equals("keshi")||jb.getString("name").equals("zd_renshu"))
					realJson = realJson+jb.getString("value")+",";
				else
					realJson = realJson+"\""+jb.getString("value")+"\",";
			}else if(i==(formArray.size()-1)){
				JSONObject jb = (JSONObject) formArray.get(i);
				realJson = realJson+"\""+jb.getString("name")+"\":";
				if(jb.getString("name").equals("kemuid")||jb.getString("name").equals("id")||jb.getString("name").equals("keshi")||jb.getString("name").equals("zd_renshu"))
					realJson = realJson+jb.getString("value")+"}";
				else
					realJson = realJson+"\""+jb.getString("value")+"\"}";
			}
		}
		
		return realJson;
	}
	public static CacheKecheng XuankeToKecheng(byte[] byteArray){
		String formStr = ParseString(byteArray);
		JSONArray formArray = JSON.parseArray(formStr);
		String realJson = "{";
		for(int i=0;i<formArray.size();i++){
			if(i!=(formArray.size()-1)){
				JSONObject jb = (JSONObject) formArray.get(i);
				realJson = realJson+"\""+jb.getString("name")+"\":";
				realJson = realJson+jb.getString("value")+",";
			}else if(i==(formArray.size()-1)){
				JSONObject jb = (JSONObject) formArray.get(i);
				realJson = realJson+"\""+jb.getString("name")+"\":";
				realJson = realJson+jb.getString("value")+"}";
			}
		}

		CacheKecheng cachekecheng = gson.fromJson(realJson, CacheKecheng.class);
		return cachekecheng;
	}
	public static String ParseString(byte[] byteArray){
		String data = null;
		try {
			data = new String(byteArray,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
//	public static void main(String args[]){
//		String data = "[{\"name\":\"xingming\",\"value\":\"敦耀\"},{\"name\":\"xingbie\",\"value\":\"男\"},{\"name\":\"cs_riqi\",\"value\":\"1832年23\"},{\"name\":\"xiaoqu\",\"value\":\"\"},{\"name\":\"banji\",\"value\":\"\"},{\"name\":\"sk_jiaoshi\",\"value\":\"\"},{\"name\":\"dizhi\",\"value\":\"\"},{\"name\":\"gd_dianhua\",\"value\":\"\"},{\"name\":\"mq_shouji\",\"value\":\"\"},{\"name\":\"fq_shouji\",\"value\":\"\"},{\"name\":\"youeryuan\",\"value\":\"\"},{\"name\":\"zc_shijian\",\"value\":\"\"},{\"name\":\"keci\",\"value\":\"\"},{\"name\":\"dycsk_shijian\",\"value\":\"\"},{\"name\":\"beizhu\",\"value\":\"\"},{\"name\":\"zhaopian\",\"value\":\"\"},{\"name\":\"jingli\",\"value\":\"\"}]";//		
//				data = ParseUtil.XueShengformToJson(data);
//		System.out.println(data);
//		Gson gson = new Gson();
//		XueSheng xuesheng = gson.fromJson(data, XueSheng.class);
////		System.out.println(xuesheng.getKeci());
//	}
}
