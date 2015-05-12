package com.shuiwen.campusys.service;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.shuiwen.campusys.bean.Banji;
import com.shuiwen.campusys.bean.Guanliyuan;
import com.shuiwen.campusys.bean.Kecheng;
import com.shuiwen.campusys.bean.Kemu;
import com.shuiwen.campusys.bean.Xiaoqu;
import com.shuiwen.campusys.bean.XueSheng;
import com.shuiwen.campusys.bean.XueshengBanji;

public interface CampusService {
    
    public List<XueSheng> findAllXueShengs(HashMap xiaoqumap);
    public XueSheng findXueShengByID(HashMap xueshengid);
    public int insertXueSheng(XueSheng xuesheng);
    public int updateXueSheng(XueSheng xuesheng);
    public XueSheng validXueSheng(XueSheng xuesheng);
    public int deleteXueSheng(HashMap xueshengid);
    
    public int validGuanliyuan(Guanliyuan guanliyuan);
    public List<Guanliyuan> findAllGuanliyuans();
    public int insertGuanliyuan(Guanliyuan guanliyuan);
    public Guanliyuan findGuanliyuanByID(HashMap guanliyuanid);
    public int deleteGuanliyuan(HashMap guanliyuanid);
    
    public List<Xiaoqu> findAllXiaoqus();
    public int insertXiaoqu(Xiaoqu xiaoqu);
    public Xiaoqu findXiaoquBySuoxie(Xiaoqu xiaoqu);
    
    public List<Banji> findAllBanjis(HashMap xiaoqumap);
    public Banji findBanjiByID(HashMap banjiid);
    public List<Banji> findBanjiByKemu(HashMap kemumap);
    public int insertBanji(Banji banji);
    public int updateBanji(Banji banji);
//    public Banji validBanji(Banji banji);
    public int deleteBanji(HashMap banjiid);
    public int deleteBanjiNow(HashMap banjiid);
    
    public List<Kemu> findAllKemus(HashMap xiaoqumap);
//    public Kemu findKemuByID(HashMap kemuid);
    public Kemu insertKemu(Kemu kemu);
//    public int updateKemu(Kemu kemu);
//    public Kemu validKemu(Kemu kemu);
//    public int deleteKemu(HashMap kemuid);
    
    public List<XueshengBanji> findAllXueshengBanjis(HashMap xiaoqumap);
//    public XueshengBanji findXueshengBanjiByID(HashMap xueshengbanjiid);
    public int insertXueshengBanji(List<XueshengBanji> xueshengbanji);
//    public int updateXueshengBanji(XueshengBanji xueshengbanji);
//    public XueshengBanji validXueshengBanji(XueshengBanji xueshengbanji);
//    public int deleteXueshengBanji(HashMap xueshengbanjiid);
    
}
