package com.shuiwen.campusys.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.shuiwen.campusys.bean.XueshengKecheng;
import com.shuiwen.campusys.dao.XueshengKechengDAO;

public class XueshengKechengDAOImpl extends SqlSessionDaoSupport implements XueshengKechengDAO {
	int doStatus;

	@Override
	public int insertXueshengKecheng(List<XueshengKecheng> listxueshengbanji) {
		// TODO Auto-generated method stub
		for(XueshengKecheng xueshengbanji:listxueshengbanji){
			doStatus = -1;
			if(super.getSqlSession().insert("XueshengKechengMapper.insertXueshengKecheng", xueshengbanji)>0){
				doStatus = super.getSqlSession().insert("KechengMapper.insertKechengXueSheng", xueshengbanji.getKechengid());
			}
        }
		
		if(doStatus>0){
			return 1;
		}else{
			return 0;
		}
	}
	
	@Override
	public int updateXueshengKecheng(XueshengKecheng xueshengbanji) {
		// TODO Auto-generated method stub
		try{
			doStatus = super.getSqlSession().update("XueshengKechengMapper.updateXueshengKecheng", xueshengbanji);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		if(doStatus>0){
			return 1;
		}else{
			return 0;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<XueshengKecheng> findAllXueshengKechengs(HashMap xiaoqumap) {
		// TODO Auto-generated method stub
		List<XueshengKecheng> xueshengbanjis = null;
		int xiaoquid = (Integer)xiaoqumap.get("xiaoquid");
		if(xiaoquid >0){
			xueshengbanjis = (List<XueshengKecheng>)super.getSqlSession().selectList("XueshengKechengMapper.findAllXueshengKechengsByXiaoqu",xiaoqumap);
		}else{
			xueshengbanjis = (List<XueshengKecheng>)super.getSqlSession().selectList("XueshengKechengMapper.findAllXueshengKechengs");
		}
		return xueshengbanjis;
	}
	
	@Override
	public XueshengKecheng findXueshengKechengByID(HashMap xueshengkecheng) {
		// TODO Auto-generated method stub
		return (XueshengKecheng) super.getSqlSession().selectOne("XueshengKechengMapper.findXueshengKechengByID",xueshengkecheng);
	}

	@Override
	public int deleteXueshengKecheng(HashMap hashMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().delete("XueshengKechengMapper.deleteXueshengKecheng", hashMap);
      
	}


	@Override
	public XueshengKecheng validXueshengKecheng(XueshengKecheng xueshengbanji) {
		// TODO Auto-generated method stub
		XueshengKecheng backxueshengbanji = (XueshengKecheng) super.getSqlSession().selectOne("XueshengKechengMapper.validXueshengKecheng", xueshengbanji);
		return backxueshengbanji;
	}

	
}
