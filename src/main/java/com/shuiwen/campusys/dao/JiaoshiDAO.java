package com.shuiwen.campusys.dao;

import java.util.List;

import com.shuiwen.campusys.bean.Jiaoshi;

public interface JiaoshiDAO {

    public Boolean insertJiaoshi(Jiaoshi jiaoshi);
  
    public List<Jiaoshi> findAllJiaoshis();
    
	public Jiaoshi findJiaoshi();
		
    
    public boolean deleteJiaoshi(Jiaoshi jiaoshi);
    
    public boolean updateJiaoshi(Jiaoshi jiaoshi);
    

}
