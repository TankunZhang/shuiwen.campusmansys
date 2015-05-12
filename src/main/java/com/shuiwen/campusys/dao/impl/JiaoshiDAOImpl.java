package com.shuiwen.campusys.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.shuiwen.campusys.bean.Jiaoshi;
import com.shuiwen.campusys.dao.JiaoshiDAO;

public class JiaoshiDAOImpl extends SqlSessionDaoSupport implements JiaoshiDAO {
	int doStatus;

	@Override
	public Boolean insertJiaoshi(Jiaoshi jiaoshi) {
		// TODO Auto-generated method stub
		try{
			doStatus = super.getSqlSession().insert("JiaoshiMapper.insertJiaoshi", jiaoshi);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		if(doStatus>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Jiaoshi> findAllJiaoshis() {
		// TODO Auto-generated method stub
		List<Jiaoshi> jiaoshis = (List<Jiaoshi>) super.getSqlSession().selectList("JiaoshiMapper.findAllJiaoshis");
		return jiaoshis;
	}
	
	@Override
	public Jiaoshi findJiaoshi() {
		// TODO Auto-generated method stub
		Jiaoshi jiaoshi = (Jiaoshi) super.getSqlSession().selectOne("JiaoshiMapper.findJiaoshi");
		return jiaoshi;
	}

	@Override
	public boolean deleteJiaoshi(Jiaoshi jiaoshi) {
		// TODO Auto-generated method stub
		int row = super.getSqlSession().delete("JiaoshiMapper.deleteJiaoshi", jiaoshi);
        if(row>0){
            return true;
        }else{
            return false;
        }
	}

	@Override
	public boolean updateJiaoshi(Jiaoshi jiaoshi) {
		// TODO Auto-generated method stub
		int row = super.getSqlSession().update("JiaoshiMapper.updateJiaoshi", jiaoshi);
		if(row>0){
			return true;
		}else{
			return false;
		}
	}

	
}
