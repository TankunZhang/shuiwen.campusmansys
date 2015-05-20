package com.shuiwen.campusys.service;

import java.util.HashMap;
import java.util.List;

import com.shuiwen.campusys.bean.Kecheng;
import com.shuiwen.campusys.bean.Guanliyuan;
import com.shuiwen.campusys.bean.Kemu;
import com.shuiwen.campusys.bean.Xiaoqu;
import com.shuiwen.campusys.bean.XueSheng;
import com.shuiwen.campusys.bean.XueshengKecheng;

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
    
    public List<Kecheng> findAllKechengs(HashMap xiaoqumap);
    public Kecheng findKechengByID(HashMap kechengid);
    public List<Kecheng> findKechengByKemu(HashMap kemumap);
    public int insertKecheng(Kecheng kecheng);
    public int updateKecheng(Kecheng kecheng);
//    public Kecheng validKecheng(Kecheng kecheng);
    public int deleteKecheng(HashMap kechengid);
    public int deleteKechengNow(HashMap kechengid);
    
    public List<Kemu> findAllKemus(HashMap xiaoqumap);
//    public Kemu findKemuByID(HashMap kemuid);
    public Kemu insertKemu(Kemu kemu);
//    public int updateKemu(Kemu kemu);
//    public Kemu validKemu(Kemu kemu);
//    public int deleteKemu(HashMap kemuid);
    
    public List<XueshengKecheng> findAllXueshengKechengs(HashMap xiaoqumap);
//    public XueshengKecheng findXueshengKechengByID(HashMap xueshengkechengid);
    public int insertXueshengKecheng(List<XueshengKecheng> xueshengkecheng);
//    public int updateXueshengKecheng(XueshengKecheng xueshengkecheng);
//    public XueshengKecheng validXueshengKecheng(XueshengKecheng xueshengkecheng);
//    public int deleteXueshengKecheng(HashMap xueshengkechengid);
    
}
