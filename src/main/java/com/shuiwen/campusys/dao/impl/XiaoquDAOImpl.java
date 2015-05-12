package com.shuiwen.campusys.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.shuiwen.campusys.bean.Guanliyuan;
import com.shuiwen.campusys.bean.Xiaoqu;
import com.shuiwen.campusys.dao.XiaoquDAO;

public class XiaoquDAOImpl extends SqlSessionDaoSupport implements XiaoquDAO {
	int doStatus;

	@Override
    public int insertXiaoqu(Xiaoqu xiaoqu) {
		// TODO Auto-generated method stub
		try{
			doStatus = super.getSqlSession().insert("XiaoquMapper.insertXiaoqu", xiaoqu);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		if(doStatus>0){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public List<Xiaoqu> findAllXiaoqus() {
		// TODO Auto-generated method stub
		List<Xiaoqu> xiaoqus = (List<Xiaoqu>) super.getSqlSession().selectList("XiaoquMapper.findAllXiaoqus");
		return xiaoqus;
	}

	
	@Override
	public boolean deleteXiaoqu(HashMap hashMap) {
		// TODO Auto-generated method stub
		int row = super.getSqlSession().delete("XiaoquMapper.deleteXiaoqu", hashMap);
        if(row>0){
            return true;
        }else{
            return false;
        }
	}

	@Override
	public boolean updateXiaoqu(Xiaoqu xiaoqu) {
		// TODO Auto-generated method stub
		int row = super.getSqlSession().update("XiaoquMapper.updateXiaoqu", xiaoqu);
		if(row>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Xiaoqu findXiaoquBySuoxie(Xiaoqu xiaoqu) {
		// TODO Auto-generated method stub
		return (Xiaoqu)super.getSqlSession().selectOne("XiaoquMapper.findXiaoquBySuoxie",xiaoqu);
		
	}

}
