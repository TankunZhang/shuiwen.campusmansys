package com.shuiwen.campusys.dao;

import java.util.HashMap;
import java.util.List;

import com.shuiwen.campusys.bean.Banji;

public interface BanjiDAO {

	public Banji validBanji(Banji banji);
	
    public int insertBanji(Banji banji);
  
    
    public List<Banji> findAllBanjis(HashMap xiaoqumap);
    
	public Banji findBanjiByID(HashMap banjiid);
	
	public List<Banji> findBanjiByKemu(HashMap kemumap);
    
    public int deleteBanji(HashMap hashMap);
    
    public int deleteBanjiNow(HashMap hashMap);
    
    public int updateBanji(Banji banji);
    

}
