package com.shuiwen.campusys.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.shuiwen.campusys.bean.Banji;
import com.shuiwen.campusys.dao.BanjiDAO;

public class BanjiDAOImpl extends SqlSessionDaoSupport implements BanjiDAO {
	int doStatus;

	@Override
	public int insertBanji(Banji banji) {
		// TODO Auto-generated method stub
		try{
			doStatus = super.getSqlSession().insert("BanjiMapper.insertBanji", banji);
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
	public int updateBanji(Banji banji) {
		// TODO Auto-generated method stub
		try{
			doStatus = super.getSqlSession().update("BanjiMapper.updateBanji", banji);
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
	public List<Banji> findAllBanjis(HashMap xiaoqumap) {
		// TODO Auto-generated method stub
		List<Banji> banjis = null;
		int xiaoquid = (Integer)xiaoqumap.get("xiaoquid");
		if(xiaoquid >0){
			banjis = (List<Banji>)super.getSqlSession().selectList("BanjiMapper.findAllBanjisByXiaoqu",xiaoqumap);
		}else{
			banjis = (List<Banji>)super.getSqlSession().selectList("BanjiMapper.findAllBanjis");
		}
		return banjis;
	}
	
	@Override
	public Banji findBanjiByID(HashMap banjiid) {
		// TODO Auto-generated method stub
		return (Banji) super.getSqlSession().selectOne("BanjiMapper.findBanjiByID",banjiid);
	}
	
	@Override
	public List<Banji> findBanjiByKemu(HashMap kemumap) {
		// TODO Auto-generated method stub
		return (List<Banji>) super.getSqlSession().selectList("BanjiMapper.findBanjiByKemu",kemumap);
	}

	@Override
	public int deleteBanji(HashMap banjiid) {
		// TODO Auto-generated method stub
		return super.getSqlSession().delete("BanjiMapper.deleteBanji", banjiid);
      
	}
	
	@Override
	public int deleteBanjiNow(HashMap banjiid) {
		// TODO Auto-generated method stub
		return super.getSqlSession().update("BanjiMapper.deleteBanjiNow", banjiid);
      
	}


	@Override
	public Banji validBanji(Banji banji) {
		// TODO Auto-generated method stub
		Banji backbanji = (Banji) super.getSqlSession().selectOne("BanjiMapper.validBanji", banji);
		return backbanji;
	}

	
}
