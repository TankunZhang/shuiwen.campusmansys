package com.shuiwen.campusys.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.shuiwen.campusys.bean.Banji;
import com.shuiwen.campusys.bean.Guanliyuan;
import com.shuiwen.campusys.bean.Kecheng;
import com.shuiwen.campusys.bean.Kemu;
import com.shuiwen.campusys.bean.Xiaoqu;
import com.shuiwen.campusys.bean.XueSheng;
import com.shuiwen.campusys.bean.XueshengBanji;
import com.shuiwen.campusys.dao.BanjiDAO;
import com.shuiwen.campusys.dao.GuanliyuanDAO;
import com.shuiwen.campusys.dao.KechengDAO;
import com.shuiwen.campusys.dao.KemuDAO;
import com.shuiwen.campusys.dao.XiaoquDAO;
import com.shuiwen.campusys.dao.XueShengDAO;
import com.shuiwen.campusys.dao.XueshengBanjiDAO;
import com.shuiwen.campusys.service.CampusService;

@Service
public class CampusServiceImpl implements CampusService {
    private static final Logger LOGGER = Logger.getLogger(CampusServiceImpl.class);

   
    @Autowired
    private XueShengDAO xueshengDAO;
    @Autowired
    private GuanliyuanDAO guanliyuanDAO;
    @Autowired
    private KechengDAO kechengDAO;
    @Autowired
    private XiaoquDAO xiaoquDAO;
    @Autowired
    private BanjiDAO banjiDAO;
    @Autowired
    private KemuDAO kemuDAO;
    @Autowired
    private XueshengBanjiDAO xueshengbanjiDAO;
    
    private static Gson gson = new Gson();
    

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
    public static SimpleDateFormat minutesFormat = new SimpleDateFormat("mm");
    private static List<String> showPayCategoryList = new ArrayList<String>();
    private static List<String> showBusinessCategoryList = new ArrayList<String>();

    public static final long MILLISECONDS_OF_HOUR = 60 * 60 * 1000;
    private static final int HOUR_INTERVAL = 3;

    static {
        showPayCategoryList.add("棋牌");
        showPayCategoryList.add("单机");
        showPayCategoryList.add("联网");
        showPayCategoryList.add("应用");
        showPayCategoryList.add("游戏");
        showPayCategoryList.add("渠道");

        showBusinessCategoryList.add("LW");
        showBusinessCategoryList.add("DJ");
        showBusinessCategoryList.add("QD");
        showBusinessCategoryList.add("DSF");
        showBusinessCategoryList.add("USERSUM");
        showBusinessCategoryList.add("TY");
        showBusinessCategoryList.add("DZF");
        showBusinessCategoryList.add("BAOYUE");
        showBusinessCategoryList.add("DX");
        showBusinessCategoryList.add("AI");
        showBusinessCategoryList.add("HE");
    }

	
	@Override
	public List<XueSheng> findAllXueShengs(HashMap xiaoqumap) {
		// TODO Auto-generated method stub
		List<XueSheng> allXueShengs = xueshengDAO.findAllXueShengs(xiaoqumap);
        return allXueShengs;
	}


	@Override
	public XueSheng findXueShengByID(HashMap xueshengid) {
		// TODO Auto-generated method stub
		return xueshengDAO.findXueShengByID(xueshengid);
	}

	@Override
	public int updateXueSheng(XueSheng xuesheng) {
		// TODO Auto-generated method stub
		return xueshengDAO.updateXueSheng(xuesheng);
	}
	

	@Override
	public int validGuanliyuan(Guanliyuan guanliyuan) {
		// TODO Auto-generated method stub
		
		return guanliyuanDAO.validGuanliyuan(guanliyuan);
	}


	@Override
	public int insertXueSheng(XueSheng xuesheng) {
		// TODO Auto-generated method stub
		return xueshengDAO.insertXueSheng(xuesheng);
	}
	
	@Override
	public int deleteXueSheng(HashMap xueshengid) {
		// TODO Auto-generated method stub
		return xueshengDAO.deleteXueSheng(xueshengid);
	}


	@Override
	public List<Guanliyuan> findAllGuanliyuans() {
		// TODO Auto-generated method stub
		List<Guanliyuan> allGuanliyuans = guanliyuanDAO.findAllGuanliyuans();
        return allGuanliyuans;
	}


	@Override
	public int insertGuanliyuan(Guanliyuan guanliyuan) {
		// TODO Auto-generated method stub
		return guanliyuanDAO.insertGuanliyuan(guanliyuan);
	}
	
	@Override
	public Guanliyuan findGuanliyuanByID(HashMap guanliyuanid) {
		// TODO Auto-generated method stub
		return guanliyuanDAO.findGuanliyuanByID(guanliyuanid);
	}
	
	@Override
	public int deleteGuanliyuan(HashMap guanliyuanid) {
		// TODO Auto-generated method stub
		return guanliyuanDAO.deleteGuanliyuan(guanliyuanid);
	}


	@Override
	public XueSheng validXueSheng(XueSheng xuesheng) {
		// TODO Auto-generated method stub
		return xueshengDAO.validXueSheng(xuesheng);
	}
	

	@Override
	public List<Xiaoqu> findAllXiaoqus() {
		// TODO Auto-generated method stub
		List<Xiaoqu> allXueShengs = xiaoquDAO.findAllXiaoqus();
        return allXueShengs;
	}


	@Override
	public int insertXiaoqu(Xiaoqu xiaoqu) {
		// TODO Auto-generated method stub
		return xiaoquDAO.insertXiaoqu(xiaoqu);
	}


	@Override
	public Xiaoqu findXiaoquBySuoxie(Xiaoqu xiaoqu) {
		// TODO Auto-generated method stub
		return xiaoquDAO.findXiaoquBySuoxie(xiaoqu);
	}
	
	@Override
	public List<Banji> findAllBanjis(HashMap xiaoqumap) {
		// TODO Auto-generated method stub
		List<Banji> allBanjis = banjiDAO.findAllBanjis(xiaoqumap);
        return allBanjis;
	}
	@Override
	public Banji findBanjiByID(HashMap banjiid) {
		// TODO Auto-generated method stub
		return banjiDAO.findBanjiByID(banjiid);
	}
	
	@Override
	public List<Banji> findBanjiByKemu(HashMap kemumap) {
		// TODO Auto-generated method stub
		return banjiDAO.findBanjiByKemu(kemumap);
	}
	
	@Override
	public int updateBanji(Banji banji) {
		// TODO Auto-generated method stub
		return banjiDAO.updateBanji(banji);
	}
	
	@Override
	public int insertBanji(Banji banji) {
		// TODO Auto-generated method stub
		return banjiDAO.insertBanji(banji);
	}
	
	@Override
	public int deleteBanji(HashMap banjiid) {
		// TODO Auto-generated method stub
		return banjiDAO.deleteBanji(banjiid);
	}
	@Override
	public int deleteBanjiNow(HashMap banjiid) {
		// TODO Auto-generated method stub
		return banjiDAO.deleteBanjiNow(banjiid);
	}

	@Override
	public List<Kemu> findAllKemus(HashMap xiaoqumap) {
		// TODO Auto-generated method stub
		List<Kemu> allKemus = kemuDAO.findAllKemus(xiaoqumap);
        return allKemus;
	}
	@Override
	public Kemu insertKemu(Kemu kemu) {
		// TODO Auto-generated method stub
		return kemuDAO.insertKemu(kemu);
	}
	
	@Override
	public int insertXueshengBanji(List<XueshengBanji> xueshengbanji) {
		// TODO Auto-generated method stub
		return xueshengbanjiDAO.insertXueshengBanji(xueshengbanji);
	}
	@Override
	public List<XueshengBanji> findAllXueshengBanjis(HashMap xiaoqumap) {
		// TODO Auto-generated method stub
		List<XueshengBanji> allXueshengBanjis = xueshengbanjiDAO.findAllXueshengBanjis(xiaoqumap);
        return allXueshengBanjis;
	}
	
}
