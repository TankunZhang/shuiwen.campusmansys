package com.shuiwen.campusys.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.shuiwen.campusys.bean.XueshengBanji;
import com.shuiwen.campusys.dao.XueshengBanjiDAO;

public class XueshengBanjiDAOImpl extends SqlSessionDaoSupport implements XueshengBanjiDAO {
	int doStatus;

	@Override
	public int insertXueshengBanji(List<XueshengBanji> listxueshengbanji) {
		// TODO Auto-generated method stub
		for(XueshengBanji xueshengbanji:listxueshengbanji){
			doStatus = -1;
			if(super.getSqlSession().insert("XueshengBanjiMapper.insertXueshengBanji", xueshengbanji)>0){
				doStatus = super.getSqlSession().insert("BanjiMapper.insertBanjiXueSheng", xueshengbanji.getBanjiid());
			}
        }
		
		if(doStatus>0){
			return 1;
		}else{
			return 0;
		}
	}
	
	@Override
	public int updateXueshengBanji(XueshengBanji xueshengbanji) {
		// TODO Auto-generated method stub
		try{
			doStatus = super.getSqlSession().update("XueshengBanjiMapper.updateXueshengBanji", xueshengbanji);
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
	public List<XueshengBanji> findAllXueshengBanjis(HashMap xiaoqumap) {
		// TODO Auto-generated method stub
		List<XueshengBanji> xueshengbanjis = null;
		int xiaoquid = (Integer)xiaoqumap.get("xiaoquid");
		if(xiaoquid >0){
			xueshengbanjis = (List<XueshengBanji>)super.getSqlSession().selectList("XueshengBanjiMapper.findAllXueshengBanjisByXiaoqu",xiaoqumap);
		}else{
			xueshengbanjis = (List<XueshengBanji>)super.getSqlSession().selectList("XueshengBanjiMapper.findAllXueshengBanjis");
		}
		return xueshengbanjis;
	}
	
	@Override
	public XueshengBanji findXueshengBanjiByID(HashMap xueshengbanjiid) {
		// TODO Auto-generated method stub
		return (XueshengBanji) super.getSqlSession().selectOne("XueshengBanjiMapper.findXueshengBanjiByID",xueshengbanjiid);
	}

	@Override
	public int deleteXueshengBanji(HashMap hashMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().delete("XueshengBanjiMapper.deleteXueshengBanji", hashMap);
      
	}


	@Override
	public XueshengBanji validXueshengBanji(XueshengBanji xueshengbanji) {
		// TODO Auto-generated method stub
		XueshengBanji backxueshengbanji = (XueshengBanji) super.getSqlSession().selectOne("XueshengBanjiMapper.validXueshengBanji", xueshengbanji);
		return backxueshengbanji;
	}

	
}
