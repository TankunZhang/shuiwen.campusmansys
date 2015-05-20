package com.shuiwen.campusys.dao;

import java.util.HashMap;
import java.util.List;

import com.shuiwen.campusys.bean.XueshengKecheng;

public interface XueshengKechengDAO {

	public XueshengKecheng validXueshengKecheng(XueshengKecheng xueshengkecheng);
	
    public int insertXueshengKecheng(List<XueshengKecheng> xueshengkecheng);
  
    
    public List<XueshengKecheng> findAllXueshengKechengs(HashMap xiaoqumap);
    
	public XueshengKecheng findXueshengKechengByID(HashMap xueshengkechengid);
		
    
    public int deleteXueshengKecheng(HashMap hashMap);
    
    public int updateXueshengKecheng(XueshengKecheng xueshengkecheng);
    

}
