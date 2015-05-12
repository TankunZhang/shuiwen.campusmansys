package com.shuiwen.campusys.dao;

import java.util.HashMap;
import java.util.List;

import com.shuiwen.campusys.bean.Kemu;

public interface KemuDAO {

	public Kemu validKemu(Kemu kemu);
	
    public Kemu insertKemu(Kemu kemu);
  
    
    public List<Kemu> findAllKemus(HashMap xiaoqumap);
    
	public Kemu findKemuByID(HashMap kemuid);
		
    
    public int deleteKemu(HashMap hashMap);
    
    public int updateKemu(Kemu kemu);
    

}
