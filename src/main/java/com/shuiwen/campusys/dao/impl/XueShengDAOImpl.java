package com.shuiwen.campusys.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.shuiwen.campusys.bean.XueSheng;
import com.shuiwen.campusys.dao.XueShengDAO;

public class XueShengDAOImpl extends SqlSessionDaoSupport implements XueShengDAO {
	int doStatus;

	@Override
	public int insertXueSheng(XueSheng xuesheng) {
		// TODO Auto-generated method stub
		try{
			doStatus = super.getSqlSession().insert("XueShengMapper.insertXueSheng", xuesheng);
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
	public int updateXueSheng(XueSheng xuesheng) {
		// TODO Auto-generated method stub
		try{
			doStatus = super.getSqlSession().update("XueShengMapper.updateXueSheng", xuesheng);
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
	public List<XueSheng> findAllXueShengs(HashMap xiaoqumap) {
		// TODO Auto-generated method stub
		List<XueSheng> xueshengs = null;
		int xiaoquid = (Integer)xiaoqumap.get("xiaoquid");
		if(xiaoquid >0){
			xueshengs = (List<XueSheng>)super.getSqlSession().selectList("XueShengMapper.findAllXueShengsByXiaoqu",xiaoqumap);
		}else{
			xueshengs = (List<XueSheng>)super.getSqlSession().selectList("XueShengMapper.findAllXueShengs");
		}
		return xueshengs;
	}
	
	@Override
	public XueSheng findXueShengByID(HashMap xueshengid) {
		// TODO Auto-generated method stub
		return (XueSheng) super.getSqlSession().selectOne("XueShengMapper.findXueShengByID",xueshengid);
	}

	@Override
	public int deleteXueSheng(HashMap hashMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().delete("XueShengMapper.deleteXueSheng", hashMap);
      
	}


	@Override
	public XueSheng validXueSheng(XueSheng xuesheng) {
		// TODO Auto-generated method stub
		XueSheng backxuesheng = (XueSheng) super.getSqlSession().selectOne("XueShengMapper.validXueSheng", xuesheng);
		return backxuesheng;
	}

	
}
