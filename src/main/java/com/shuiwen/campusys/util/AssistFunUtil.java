package com.shuiwen.campusys.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shuiwen.campusys.bean.CacheKecheng;
import com.shuiwen.campusys.bean.Guanliyuan;
import com.shuiwen.campusys.bean.XueshengKecheng;

public class AssistFunUtil {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<XueshengKecheng>  XueKechengToList(HashMap xuankes,String shoukuanren,int xueshengid){
		List<XueshengKecheng> reXueshengKecheng = new ArrayList<XueshengKecheng>();
		Iterator<Integer> it = xuankes.keySet().iterator();
        while (it.hasNext()) {
            int key = it.next();
            CacheKecheng cachekecheng = (CacheKecheng)xuankes.get(key);
            XueshengKecheng xueshengkecheng = new XueshengKecheng();
            
            xueshengkecheng.setXueshengid(xueshengid);
            xueshengkecheng.setBanjiid(cachekecheng.getKechengid());
            xueshengkecheng.setBj_mingzi(cachekecheng.getBj_mingzi());
            xueshengkecheng.setShoukuanren(shoukuanren);
            
            reXueshengKecheng.add(xueshengkecheng);
        }
		return reXueshengKecheng;
	}

}
