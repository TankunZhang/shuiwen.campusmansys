package com.shuiwen.campusys.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.shuiwen.campusys.bean.Guanliyuan;
import com.shuiwen.campusys.bean.XueSheng;
import com.shuiwen.campusys.dao.GuanliyuanDAO;

public class GuanliyuanDAOImpl extends SqlSessionDaoSupport implements GuanliyuanDAO {
	int doStatus;

	@Override
    public int insertGuanliyuan(Guanliyuan guanliyuan) {
		// TODO Auto-generated method stub
		try{
			doStatus = super.getSqlSession().insert("GuanliyuanMapper.insertGuanliyuan", guanliyuan);
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
	public List<Guanliyuan> findAllGuanliyuans() {
		// TODO Auto-generated method stub
		List<Guanliyuan> guanliyuans = (List<Guanliyuan>) super.getSqlSession().selectList("GuanliyuanMapper.findAllGuanliyuans");
		return guanliyuans;
	}

	@Override
	public Guanliyuan findGuanliyuan() {
		// TODO Auto-generated method stub
		Guanliyuan guanliyuan = (Guanliyuan) super.getSqlSession().selectOne("GuanliyuanMapper.findGuanliyuan");
		return guanliyuan;
	}
	
	@Override
	public Guanliyuan findGuanliyuanByID(HashMap guanliyuanid) {
		// TODO Auto-generated method stub
		return (Guanliyuan) super.getSqlSession().selectOne("GuanliyuanMapper.findGuanliyuanByID",guanliyuanid);
	}
	
	@Override
	public int deleteGuanliyuan(HashMap hashMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().delete("GuanliyuanMapper.deleteGuanliyuan", hashMap);
        
	}

	@Override
	public boolean updateGuanliyuan(Guanliyuan guanliyuan) {
		// TODO Auto-generated method stub
		int row = super.getSqlSession().update("GuanliyuanMapper.updateGuanliyuan", guanliyuan);
		if(row>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int validGuanliyuan(Guanliyuan guanliyuan) {
		// TODO Auto-generated method stub
		int real = Integer.valueOf(String.valueOf(super.getSqlSession().selectOne("GuanliyuanMapper.validGuanliyuan", guanliyuan)));
		return real;
	}



	
}
