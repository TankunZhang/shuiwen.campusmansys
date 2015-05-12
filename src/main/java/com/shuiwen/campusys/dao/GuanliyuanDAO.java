package com.shuiwen.campusys.dao;

import java.util.HashMap;
import java.util.List;

import com.shuiwen.campusys.bean.Guanliyuan;

public interface GuanliyuanDAO {

    public int insertGuanliyuan(Guanliyuan guanliyuan);
  
    public List<Guanliyuan> findAllGuanliyuans();
    
	public Guanliyuan findGuanliyuan();
	
	public Guanliyuan findGuanliyuanByID(HashMap guanliyuanid);
	
	public int validGuanliyuan(Guanliyuan guanliyuan);
    
    public int deleteGuanliyuan(HashMap hashMap);
    
    public boolean updateGuanliyuan(Guanliyuan guanliyuan);
    

}
