package com.shuiwen.campusys.dao;

import java.util.HashMap;
import java.util.List;

import com.shuiwen.campusys.bean.XueshengBanji;

public interface XueshengBanjiDAO {

	public XueshengBanji validXueshengBanji(XueshengBanji xueshengbanji);
	
    public int insertXueshengBanji(List<XueshengBanji> xueshengbanji);
  
    
    public List<XueshengBanji> findAllXueshengBanjis(HashMap xiaoqumap);
    
	public XueshengBanji findXueshengBanjiByID(HashMap xueshengbanjiid);
		
    
    public int deleteXueshengBanji(HashMap hashMap);
    
    public int updateXueshengBanji(XueshengBanji xueshengbanji);
    

}
