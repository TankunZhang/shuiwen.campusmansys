package com.shuiwen.campusys.dao;

import java.util.HashMap;
import java.util.List;

import com.shuiwen.campusys.bean.Kecheng;

public interface KechengDAO {

	public Kecheng validKecheng(Kecheng kecheng);
	
    public int insertKecheng(Kecheng kecheng);
  
    
    public List<Kecheng> findAllKechengs(HashMap xiaoqumap);
    
	public Kecheng findKechengByID(HashMap kechengid);
	
	public List<Kecheng> findKechengByKemu(HashMap kemumap);
    
    public int deleteKecheng(HashMap hashMap);
    
    public int deleteKechengNow(HashMap hashMap);
    
    public int updateKecheng(Kecheng kecheng);
    

}
