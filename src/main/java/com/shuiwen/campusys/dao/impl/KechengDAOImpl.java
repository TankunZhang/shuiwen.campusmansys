package com.shuiwen.campusys.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.shuiwen.campusys.bean.Kecheng;
import com.shuiwen.campusys.dao.KechengDAO;

public class KechengDAOImpl extends SqlSessionDaoSupport implements KechengDAO {
	int doStatus;

	@Override
	public int insertKecheng(Kecheng kecheng) {
		// TODO Auto-generated method stub
		try{
			doStatus = super.getSqlSession().insert("KechengMapper.insertKecheng", kecheng);
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
	public int updateKecheng(Kecheng kecheng) {
		// TODO Auto-generated method stub
		try{
			doStatus = super.getSqlSession().update("KechengMapper.updateKecheng", kecheng);
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
	public List<Kecheng> findAllKechengs(HashMap xiaoqumap) {
		// TODO Auto-generated method stub
		List<Kecheng> kechengs = null;
		int xiaoquid = (Integer)xiaoqumap.get("xiaoquid");
		if(xiaoquid >0){
			kechengs = (List<Kecheng>)super.getSqlSession().selectList("KechengMapper.findAllKechengsByXiaoqu",xiaoqumap);
		}else{
			kechengs = (List<Kecheng>)super.getSqlSession().selectList("KechengMapper.findAllKechengs");
		}
		return kechengs;
	}
	
	@Override
	public Kecheng findKechengByID(HashMap kechengid) {
		// TODO Auto-generated method stub
		return (Kecheng) super.getSqlSession().selectOne("KechengMapper.findKechengByID",kechengid);
	}
	
	@Override
	public List<Kecheng> findKechengByKemu(HashMap kemumap) {
		// TODO Auto-generated method stub
		return (List<Kecheng>) super.getSqlSession().selectList("KechengMapper.findKechengByKemu",kemumap);
	}

	@Override
	public int deleteKecheng(HashMap kechengid) {
		// TODO Auto-generated method stub
		return super.getSqlSession().delete("KechengMapper.deleteKecheng", kechengid);
      
	}
	
	@Override
	public int deleteKechengNow(HashMap kechengid) {
		// TODO Auto-generated method stub
		return super.getSqlSession().update("KechengMapper.deleteKechengNow", kechengid);
      
	}


	@Override
	public Kecheng validKecheng(Kecheng kecheng) {
		// TODO Auto-generated method stub
		Kecheng backkecheng = (Kecheng) super.getSqlSession().selectOne("KechengMapper.validKecheng", kecheng);
		return backkecheng;
	}

	
}
