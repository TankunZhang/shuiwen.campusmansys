package com.shuiwen.campusys.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.shuiwen.campusys.bean.Kecheng;
import com.shuiwen.campusys.dao.KechengDAO;

public class KechengDAOImpl extends SqlSessionDaoSupport implements KechengDAO {
	int doStatus;

	

	@Override
	public List<Kecheng> findAllKechengs() {
		// TODO Auto-generated method stub
		List<Kecheng> kechengs = (List<Kecheng>) super.getSqlSession().selectList("KechengMapper.findAllKechengs");
		return kechengs;
	}
	
	
}
