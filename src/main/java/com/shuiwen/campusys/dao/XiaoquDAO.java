package com.shuiwen.campusys.dao;

import java.util.HashMap;
import java.util.List;

import com.shuiwen.campusys.bean.Xiaoqu;

public interface XiaoquDAO {

    public int insertXiaoqu(Xiaoqu xiaoqu);
  
    public List<Xiaoqu> findAllXiaoqus();
    
    public boolean deleteXiaoqu(HashMap hashMap);
    
    public boolean updateXiaoqu(Xiaoqu xiaoqu);
    
    public Xiaoqu findXiaoquBySuoxie(Xiaoqu xiaoqu);
    

}
