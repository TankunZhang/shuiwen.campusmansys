package com.shuiwen.campusys.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shuiwen.campusys.bean.CacheKecheng;
import com.shuiwen.campusys.bean.Guanliyuan;
import com.shuiwen.campusys.bean.XueshengBanji;

public class AssistFunUtil {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<XueshengBanji>  XueBanjiToList(HashMap xuankes,String shoukuanren,int xueshengid){
		List<XueshengBanji> reXueshengBanji = new ArrayList<XueshengBanji>();
		Iterator<Integer> it = xuankes.keySet().iterator();
        while (it.hasNext()) {
            int key = it.next();
            CacheKecheng cachekecheng = (CacheKecheng)xuankes.get(key);
            XueshengBanji xueshengbanji = new XueshengBanji();
            
            xueshengbanji.setXueshengid(xueshengid);
            xueshengbanji.setBanjiid(cachekecheng.getBanjiid());
            xueshengbanji.setBj_mingzi(cachekecheng.getBj_mingzi());
            xueshengbanji.setShoukuanren(shoukuanren);
            
            reXueshengBanji.add(xueshengbanji);
        }
		return reXueshengBanji;
	}

}
