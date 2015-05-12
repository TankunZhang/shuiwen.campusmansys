package com.shuiwen.campusys.dao;

import java.util.HashMap;
import java.util.List;

import com.shuiwen.campusys.bean.XueSheng;

public interface XueShengDAO {

	public XueSheng validXueSheng(XueSheng xuesheng);
	
    public int insertXueSheng(XueSheng xuesheng);
  
    
    public List<XueSheng> findAllXueShengs(HashMap xiaoqumap);
    
	public XueSheng findXueShengByID(HashMap xueshengid);
		
    
    public int deleteXueSheng(HashMap hashMap);
    
    public int updateXueSheng(XueSheng xuesheng);
    

}
