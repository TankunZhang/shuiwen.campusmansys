package com.shuiwen.campusys.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.shuiwen.campusys.bean.Kecheng;
import com.shuiwen.campusys.bean.CacheKecheng;
import com.shuiwen.campusys.bean.Guanliyuan;
import com.shuiwen.campusys.bean.Kemu;
import com.shuiwen.campusys.bean.Xiaoqu;
import com.shuiwen.campusys.bean.XueSheng;
import com.shuiwen.campusys.bean.XueshengKecheng;
import com.shuiwen.campusys.service.CampusService;
import com.shuiwen.campusys.util.AssistFunUtil;
import com.shuiwen.campusys.util.LogUtil;
import com.shuiwen.campusys.util.ParseUtil;
import com.shuiwen.campusys.util.ResponseUtil;

@Controller
public class MainController {

    @Autowired
    private CampusService campusService;
    
    private static Gson gson = new Gson();
    
    @SuppressWarnings("rawtypes")
	private HashMap xuexuankemap = new HashMap<Integer, HashMap<Integer, CacheKecheng>>();
    
    String responseData = null;
    
    @RequestMapping(value="/findallstudents")
    @ResponseBody
    public String findAllStudents(HttpServletRequest request, HttpServletResponse response, @RequestParam("xiaoquid") int xiaoquid) throws IOException {
    	List<XueSheng> allXueShengs = null;
    	
    	try {
	        	HashMap<String, Integer> xiaoqumap = new HashMap<String, Integer>();
	        	xiaoqumap.put("xiaoquid", xiaoquid);
	        	System.out.println(xiaoquid);
	        	allXueShengs = campusService.findAllXueShengs(xiaoqumap);
	        	responseData =  ResponseUtil.buildSuccessResponse("查询学生成功",allXueShengs);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            responseData = ResponseUtil.buildSystemErrorResponse();
        }
        System.out.println(responseData);
        return responseData;
    }
    
    @RequestMapping(value="/findstudentbyid")
    @ResponseBody
    public String findStudentByID(HttpServletRequest request,@RequestParam("id") int id) throws IOException {
    	XueSheng xuesheng = null;
    	
    	try {
	        	HashMap<String, Integer> xueshengid = new HashMap<String, Integer>();
	        	xueshengid.put("id", id);
	        	xuesheng = campusService.findXueShengByID(xueshengid);
	        	responseData =  ResponseUtil.buildSuccessResponse("查询学生成功",xuesheng);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            responseData = ResponseUtil.buildSystemErrorResponse();
        }
        System.out.println(responseData);
        return responseData;
    }

    @RequestMapping(value="/validstudent")
    @ResponseBody
    public String ValidXueSheng(HttpServletRequest request, @RequestParam("xingming") String xingming, @RequestParam("cs_riqi") String cs_riqi, @RequestParam("xiaoquid") int xiaoquid) throws UnsupportedEncodingException {
    	System.out.println(xingming);
    	XueSheng xuesheng = new XueSheng();
    	xuesheng.setXingming(xingming);
    	xuesheng.setCs_riqi(cs_riqi);
    	xuesheng.setXiaoquid(xiaoquid);
    	xuesheng = campusService.validXueSheng(xuesheng);
    	if(xuesheng!=null){
    		responseData =  ResponseUtil.buildSuccessResponse("查询学生成功",xuesheng);
    	}else{
			responseData = ResponseUtil.buildSuccessResponse("本校区没有此学生", -1);
		}
        return responseData;

    }
    

    @RequestMapping(value="/addstudent")
    @ResponseBody
    public String addXueSheng(HttpServletRequest request, HttpServletResponse response) {
    	byte[] byteArray;
    	int instusuccess = -1;
		try {
			byteArray = IOUtils.toByteArray(request.getInputStream());
			String data = ParseUtil.XueShengformToJson(byteArray);
//			System.out.println("解析数据："+data);
			XueSheng xuesheng = gson.fromJson(data, XueSheng.class);
			instusuccess = campusService.insertXueSheng(xuesheng);
			if(instusuccess>0){
				responseData =  ResponseUtil.buildSuccessResponse("添加学生成功",instusuccess);
			}else{
				responseData = ResponseUtil.buildErrorResponse(0, "请求失败", null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return responseData;
    }
    
    @RequestMapping(value="/updatestudent")
    @ResponseBody
    public String updateStudent(HttpServletRequest request, HttpServletResponse response) {
    	byte[] byteArray;
    	int instusuccess = -1;
		try {
			byteArray = IOUtils.toByteArray(request.getInputStream());
			String data = ParseUtil.XueShengformToJson(byteArray);
			XueSheng xuesheng = gson.fromJson(data, XueSheng.class);
			System.out.println(gson.toJson(xuesheng));
			instusuccess = campusService.updateXueSheng(xuesheng);
			if(instusuccess>0){
				responseData =  ResponseUtil.buildSuccessResponse("添加学生成功",instusuccess);
			}else{
				responseData = ResponseUtil.buildErrorResponse(0, "请求失败", null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return responseData;
    }
    
    @RequestMapping(value="/deletestudent")
    @ResponseBody
    public String deleteXueSheng(HttpServletRequest request, @RequestParam("id") int id) {
    	int instusuccess = -1;
    	HashMap<String, Integer> xueshengid = new HashMap<String, Integer>();
    	xueshengid.put("id", id);
    	instusuccess = campusService.deleteXueSheng(xueshengid);
    	if(instusuccess>0){
    		responseData =  ResponseUtil.buildSuccessResponse("删除学生成功",instusuccess);
    	}else{
			responseData = ResponseUtil.buildErrorResponse(0, "请求失败", null);
		}
        return responseData;

    }
    
    @RequestMapping(value="/validUser")
    @ResponseBody
    public String validGuanliyuan(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {
    	Guanliyuan guanliyuan = new Guanliyuan();
    	guanliyuan.setGly_mingzi(username);
    	guanliyuan.setMima(password);
    	int realguanliyuan = campusService.validGuanliyuan(guanliyuan);
    	HashMap<String, Integer> realguanliyuanjson = new HashMap<String, Integer>();
    	realguanliyuanjson.put("realguanliyuan", realguanliyuan);
    	//        String date = request.getParameter("date");
//    
//        CategoryReport categoryReport = campusService.queryBusinessCategoryReport(category, date);
        return gson.toJson(realguanliyuanjson);
    }
    
    @RequestMapping(value="/findallusers")
    @ResponseBody
    public String findAllGuanliyuans(HttpServletRequest request, HttpServletResponse response) {
    	
    	try {
        	List<Guanliyuan> allGuanliyuans = campusService.findAllGuanliyuans();
            return gson.toJson(allGuanliyuans);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseUtil.buildSystemErrorResponse();
        }
    }
    
    @RequestMapping(value="/finduserbyid")
    @ResponseBody
    public String findGuanliyuanByID(HttpServletRequest request ,@RequestParam("id") int id) throws IOException {
    	Guanliyuan guanliyuan = null;
    	
    	try {
	        	HashMap<String, Integer> guanliyuanid = new HashMap<String, Integer>();
	        	guanliyuanid.put("id", id);
	        	guanliyuan = campusService.findGuanliyuanByID(guanliyuanid);
	        	responseData =  ResponseUtil.buildSuccessResponse("查询用户成功",guanliyuan);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            responseData = ResponseUtil.buildSystemErrorResponse();
        }
        System.out.println(responseData);
        return responseData;
    }
    
    @RequestMapping(value="adduser")
    @ResponseBody
    public String addGuanliyuan(HttpServletRequest request, HttpServletResponse response) {
    	byte[] byteArray;
    	int instusuccess = -1;
    	try {
			byteArray = IOUtils.toByteArray(request.getInputStream());
	        String data = ParseUtil.GuanliyuanformToJson(byteArray);
			Guanliyuan guanliyuan = gson.fromJson(data, Guanliyuan.class);
			instusuccess = campusService.insertGuanliyuan(guanliyuan);
			if(instusuccess>0){
				responseData =  ResponseUtil.buildSuccessResponse("添加用户成功",instusuccess);
			}else{
				responseData = ResponseUtil.buildErrorResponse(0, "请求失败", null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseData= ResponseUtil.buildSystemErrorResponse();
		}
        return responseData;
    }
    
    @RequestMapping(value="/deleteuser")
    @ResponseBody
    public String deleteGuanliyuan(HttpServletRequest request, @RequestParam("id") int id) {
    	int instusuccess = -1;
    	
	        	HashMap<String, Integer> guanliyuanid = new HashMap<String, Integer>();
	        	guanliyuanid.put("id", id);
	        	instusuccess = campusService.deleteGuanliyuan(guanliyuanid);
	        	if(instusuccess>0){
	        		responseData =  ResponseUtil.buildSuccessResponse("删除用户成功",instusuccess);
		    	}else{
					responseData = ResponseUtil.buildErrorResponse(0, "请求失败", null);
				}
        return responseData;

    }
    
    @RequestMapping(value="/findallschools")
    @ResponseBody
    public String findAllSchools(HttpServletRequest request, HttpServletResponse response) {
    	
    	try {
        	List<Xiaoqu> allXiaoqus = campusService.findAllXiaoqus();
        	if(allXiaoqus!=null){
        		responseData =  ResponseUtil.buildSuccessResponse("读取校区成功",allXiaoqus);
        	}else{
        		responseData = ResponseUtil.buildErrorResponse(0, "请求失败", null);
        	}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            responseData = ResponseUtil.buildSystemErrorResponse();
        }
    	System.out.println(responseData);
    	return responseData;
    }
    
    @RequestMapping(value="addschool")
    @ResponseBody
    public String addSchool(HttpServletRequest request, HttpServletResponse response) {
    	byte[] byteArray;
    	try {
			byteArray = IOUtils.toByteArray(request.getInputStream());
	        String data = ParseUtil.XiaoquformToJson(byteArray);
			Xiaoqu xiaoqu = gson.fromJson(data, Xiaoqu.class);
			if(campusService.insertXiaoqu(xiaoqu)>0){
				Xiaoqu rexiaoqu = campusService.findXiaoquBySuoxie(xiaoqu);
				responseData =  ResponseUtil.buildSuccessResponse("添加校区成功",rexiaoqu);
			}else{
				responseData = ResponseUtil.buildErrorResponse(0, "添加失败", null);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseData = ResponseUtil.buildSystemErrorResponse();
		}
        return responseData;
    }
    
    @RequestMapping(value="/findallclasses")
    @ResponseBody
    public String findAllClasses(HttpServletRequest request, HttpServletResponse response, @RequestParam("xiaoquid") int xiaoquid) throws IOException {
    	List<Kecheng> allKechengs = null;
    	
    	try {
	        	HashMap<String, Integer> xiaoqumap = new HashMap<String, Integer>();
	        	xiaoqumap.put("xiaoquid", xiaoquid);
	        	System.out.println(xiaoquid);
	        	allKechengs = campusService.findAllKechengs(xiaoqumap);
	        	if(allKechengs!=null){
	        		responseData =  ResponseUtil.buildSuccessResponse("查询班级成功",allKechengs);
	        	}else{
	        		responseData = ResponseUtil.buildErrorResponse(0, "查询失败", null);
	        	}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            responseData = ResponseUtil.buildSystemErrorResponse();
        }
        System.out.println(responseData);
        return responseData;
    }
    
    @RequestMapping(value="/findclassbyid")
    @ResponseBody
    public String findKechengByID(HttpServletRequest request,@RequestParam("id") int id) throws IOException {
    	Kecheng kecheng = null;
    	
    	try {
	        	HashMap<String, Integer> kechengid = new HashMap<String, Integer>();
	        	kechengid.put("id", id);
	        	kecheng = campusService.findKechengByID(kechengid);
	        	if(kecheng!=null){
	        		responseData =  ResponseUtil.buildSuccessResponse("查询班级成功",kecheng);
	        	}else{
	        		responseData = ResponseUtil.buildErrorResponse(0, "查询失败", null);
	        	}
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            responseData = ResponseUtil.buildSystemErrorResponse();
        }
        System.out.println(responseData);
        return responseData;
    }
    
    @RequestMapping(value="/findclassbysubject")
    @ResponseBody
    public String findKechengByKemu(HttpServletRequest request,@RequestParam("kemuid") int kemuid) throws IOException {
    	try {
	        	HashMap<String, Integer> kemumap = new HashMap<String, Integer>();
	        	kemumap.put("kemuid", kemuid);
	        	List<Kecheng> kechengs = campusService.findKechengByKemu(kemumap);
	        	if(kechengs!=null){
	        		responseData =  ResponseUtil.buildSuccessResponse("查询班级成功",kechengs);
	        	}else{
	        		responseData = ResponseUtil.buildErrorResponse(0, "查询失败", null);
	        	}
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            responseData = ResponseUtil.buildSystemErrorResponse();
        }
        System.out.println(responseData);
        return responseData;
    }
    
    @RequestMapping(value="/updateclass")
    @ResponseBody
    public String updateKecheng(HttpServletRequest request, HttpServletResponse response) {
    	byte[] byteArray;
    	int instusuccess = -1;
		try {
			byteArray = IOUtils.toByteArray(request.getInputStream());
	        String data = ParseUtil.KechengformToJson(byteArray);
			Kecheng kecheng = gson.fromJson(data, Kecheng.class);
			System.out.println(gson.toJson(kecheng));
			instusuccess = campusService.updateKecheng(kecheng);
			if(instusuccess>0){
				responseData =  ResponseUtil.buildSuccessResponse("添加学生成功",instusuccess);
			}else{
				responseData = ResponseUtil.buildErrorResponse(0, "请求失败", null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return responseData;
    }
    
    @RequestMapping(value="addclass")
    @ResponseBody
    public String addKecheng(HttpServletRequest request, HttpServletResponse response) {
    	byte[] byteArray;
    	int instusuccess = -1;
    	try {
			byteArray = IOUtils.toByteArray(request.getInputStream());
	        String data = ParseUtil.KechengformToJson(byteArray);
			Kecheng kecheng = gson.fromJson(data, Kecheng.class);
			instusuccess = campusService.insertKecheng(kecheng);
			if(instusuccess>0){
				responseData =  ResponseUtil.buildSuccessResponse("添加用户成功",instusuccess);
			}else{
				responseData = ResponseUtil.buildErrorResponse(0, "请求失败", null);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseData = ResponseUtil.buildSystemErrorResponse();
		}
        return responseData;
    }
    
    @RequestMapping(value="/deleteclass")
    @ResponseBody
    public String deleteKecheng(HttpServletRequest request, @RequestParam("id") int id) {
    	int instusuccess = -1;
    	HashMap<String, Integer> kechengid = new HashMap<String, Integer>();
    	kechengid.put("id", id);
    	instusuccess = campusService.deleteKecheng(kechengid);
    	if(instusuccess>0){
    		responseData =  ResponseUtil.buildSuccessResponse("删除学生成功",instusuccess);
    	}else{
			responseData = ResponseUtil.buildErrorResponse(0, "请求失败", null);
		}
        return responseData;

    }
    
    @RequestMapping(value="/deleteclassnow")
    @ResponseBody
    public String deleteKechengNow(HttpServletRequest request, @RequestParam("id") int id) {
    	int instusuccess = -1;
    	HashMap<String, Integer> kechengid = new HashMap<String, Integer>();
    	kechengid.put("id", id);
    	instusuccess = campusService.deleteKechengNow(kechengid);
    	if(instusuccess>0){
    		responseData =  ResponseUtil.buildSuccessResponse("预先删除学生成功",instusuccess);
    	}else{
			responseData = ResponseUtil.buildErrorResponse(0, "请求失败", null);
		}
        return responseData;

    }
    
    
    @RequestMapping(value="/findallsubjects")
    @ResponseBody
    public String findAllKemus(HttpServletRequest request, HttpServletResponse response, @RequestParam("xiaoquid") int xiaoquid) throws IOException {
    	List<Kemu> allKemus = null;
    	
    	try {
	        	HashMap<String, Integer> xiaoqumap = new HashMap<String, Integer>();
	        	xiaoqumap.put("xiaoquid", xiaoquid);
	        	System.out.println(xiaoquid);
	        	allKemus = campusService.findAllKemus(xiaoqumap);
	        	if(allKemus!=null){
	        		responseData =  ResponseUtil.buildSuccessResponse("查询科目成功",allKemus);
	        	}else{
	        		responseData = ResponseUtil.buildErrorResponse(0, "查询失败", null);
	        	}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            responseData = ResponseUtil.buildSystemErrorResponse();
        }
        System.out.println(responseData);
        return responseData;
    }
    
    @RequestMapping(value="/addsubject")
    @ResponseBody
    public String addKemu(HttpServletRequest request, HttpServletResponse response) {
    	byte[] byteArray;
    	int instusuccess = -1;
    	try {
			byteArray = IOUtils.toByteArray(request.getInputStream());
			String kemuyouhuijson = ParseUtil.ParseString(byteArray);
			LogUtil.AuthTitlLog("addsubject","Request", kemuyouhuijson);
	        List data = AssistFunUtil.KemuyouhuiToList(kemuyouhuijson);
        	Kemu rekemu = campusService.insertKemu((Kemu)data.get(0));
        	instusuccess = campusService.insertDankeyouhui(rekemu,(JSONArray)data.get(1));
			if(instusuccess>0){
				responseData =  ResponseUtil.buildSuccessResponse("添加成功",1);
			}else{
				responseData = ResponseUtil.buildErrorResponse(0, "请求失败", null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(responseData);
    	return responseData;
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value="addstuclass")
    @ResponseBody
    public String addXueKecheng(HttpServletRequest request, @RequestParam("xueshengid") int xueshengid,@RequestParam("shoukuanren") String shoukuanren) {
		int instusuccess = -1;
		if(xuexuankemap.containsKey(xueshengid)){
			HashMap<Integer, CacheKecheng> xuankes = (HashMap<Integer, CacheKecheng>)xuexuankemap.get(xueshengid);
	        List<XueshengKecheng> XueshengKecheng = AssistFunUtil.XueKechengToList(xuankes,shoukuanren,xueshengid);
	        instusuccess = campusService.insertXueshengKecheng(XueshengKecheng);
		}
		if(instusuccess>0){
			responseData =  ResponseUtil.buildSuccessResponse("添加课程成功",instusuccess);
		}else{
			responseData = ResponseUtil.buildErrorResponse(0, "添加失败", null);
		}
		
        return responseData;
    }
    
    @RequestMapping(value="/findallstuclasses")
    @ResponseBody
    public String findAllXueshengKechengs(HttpServletRequest request, @RequestParam("xiaoquid") int xiaoquid) {
    	
    	try {
    		HashMap<String, Integer> xiaoqumap = new HashMap<String, Integer>();
        	xiaoqumap.put("xiaoquid", xiaoquid);
        	List<XueshengKecheng> allXueshengKechengs = campusService.findAllXueshengKechengs(xiaoqumap);
        	if(allXueshengKechengs!=null){
        		responseData =  ResponseUtil.buildSuccessResponse("查询班级成功",allXueshengKechengs);
        	}else{
        		responseData = ResponseUtil.buildErrorResponse(0, "查询失败", null);
        	}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            responseData = ResponseUtil.buildSystemErrorResponse();
        }
    	
    	return responseData;
    }
    @SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping(value="/cacheapplyclasses")
    @ResponseBody
    public String CacheXueXuankes(HttpServletRequest request,HttpServletResponse response, @RequestParam("xueshengid") int xueshengid) throws IOException {
    	byte[] byteArray = IOUtils.toByteArray(request.getInputStream());
    	HashMap<Integer, CacheKecheng> xuankes = null;
        String data = ParseUtil.ParseString(byteArray);
        System.out.println("元数据："+data);
        CacheKecheng cachekecheng = gson.fromJson(data, CacheKecheng.class);
    	if(xuexuankemap.containsKey(xueshengid)){
    		xuankes = (HashMap<Integer, CacheKecheng>)xuexuankemap.get(xueshengid);
    		if(xuankes.containsKey(cachekecheng.getKechengid())){
    			xuankes.remove(cachekecheng.getKechengid());
    		}
    		xuankes.put(cachekecheng.getKechengid(), cachekecheng);
    		xuexuankemap.remove(xueshengid);
    	}else{
    		xuankes = new HashMap<Integer, CacheKecheng>();
    		xuankes.put(cachekecheng.getKechengid(), cachekecheng);
    	}
    	xuexuankemap.put(xueshengid, xuankes);
    	
    	List<CacheKecheng> rexuankelist = new ArrayList<CacheKecheng>();
        Iterator<Integer> it = xuankes.keySet().iterator();
        while (it.hasNext()) {
            int key = it.next();
            rexuankelist.add(xuankes.get(key));
        }
        if(rexuankelist!=null)
        	responseData =  ResponseUtil.buildSuccessResponse("选课成功",rexuankelist);
        else
        	responseData = ResponseUtil.buildErrorResponse(0, "选课失败", null);
        
    
        System.out.println(responseData);
        return responseData;
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/delcacheapplyclass")
    @ResponseBody
    public String DelCacheXueXuanke(HttpServletRequest request,@RequestParam("kechengid") int kechengid, @RequestParam("xueshengid") int xueshengid) throws IOException {
    	HashMap<Integer, CacheKecheng> xuankes = (HashMap<Integer, CacheKecheng>)xuexuankemap.get(xueshengid);
    	List<CacheKecheng> rexuankelist = null;
    	if(xuankes.containsKey(kechengid)){
			xuankes.remove(kechengid);
			xuexuankemap.remove(xueshengid);
	    	xuexuankemap.put(xueshengid, xuankes);
	    	rexuankelist = new ArrayList<CacheKecheng>();
	        Iterator<Integer> it = xuankes.keySet().iterator();
	        while (it.hasNext()) {
	            int key = it.next();
	            rexuankelist.add(xuankes.get(key));
	        }
		}
        if(rexuankelist!=null)
        	responseData =  ResponseUtil.buildSuccessResponse("选课成功",rexuankelist);
        else
        	responseData = ResponseUtil.buildErrorResponse(0, "选课失败", null);
        System.out.println(responseData);
        return responseData;
    }
    
    
    @RequestMapping(value="login")//请求的地址
    @ResponseBody
    public String loginReport(HttpServletRequest request, HttpServletResponse response) {
//    	UserInfo userInfo = null;
//    	String data = "";
//    	String returnMess = "";
//    	//获取对请求的验证
//    	VerifyEntity verifyEntity = VerifyUtil.verifyRequest(request, "login");
//    	if(verifyEntity.isUseful()){
//    		data = verifyEntity.getReturnMess();
//    		JSONObject json = JSON.parseObject(data);//解析http请求的值
//    		try{
//	    		userInfo =  gson.fromJson(data, UserInfo.class);//解析json的值
//	    		returnMess = campusService.hasListUser(userInfo);//响应的字符串
//    		}catch(Exception e){
//				System.out.println(e.toString());
//				returnMess = ResponseUtil.buildErrorResponse(ResponseUtil.SYSTEM_ERROR_CODE, "数据有误，与对象属性不符！", null);
//			}
//    	}else{
//    		returnMess = verifyEntity.getReturnMess();
//    	}
//		
//    	System.out.println("login;response:"+returnMess);
//        return ServerSecurity.custom_encrypt(returnMess);//响应服务
    	return null;
    }
    
    @RequestMapping(value="jointeam")
    @ResponseBody
    public String joinTeamReport(HttpServletRequest request, HttpServletResponse response) {
//    	UserInfo userInfo = null;
//    	String data = "";
//    	String returnMess = "";
//    	//获取对请求的验证
//    	VerifyEntity verifyEntity = VerifyUtil.verifyRequest(request, "jointeam");
//    	if(verifyEntity.isUseful()){
//    		data = verifyEntity.getReturnMess();
//    		JSONObject json = JSON.parseObject(data);
//    		try{
//    			userInfo =  gson.fromJson(data, UserInfo.class);
//    			returnMess = campusService.joinListUser(userInfo);
//	    	}catch(Exception e){
//				System.out.println(e.toString());
//				returnMess = ResponseUtil.buildErrorResponse(ResponseUtil.SYSTEM_ERROR_CODE, "数据有误，与对象属性不符！", null);
//			}
//    	}else{
//    		returnMess = verifyEntity.getReturnMess();
//    	}
//    	System.out.println("jointeam;response:"+returnMess);
//    	return ServerSecurity.custom_encrypt(returnMess);
    	return null;
    }
    
    @RequestMapping(value="listauth")
    @ResponseBody
    public String listAuthReport(HttpServletRequest request, HttpServletResponse response) {
//    	
//    	UserInfo userInfo = null;
//    	String data = "";
//    	String returnMess = "";
//    	//获取对请求的验证
//    	VerifyEntity verifyEntity = VerifyUtil.verifyRequest(request, "listauth");
//    	if(verifyEntity.isUseful()){
//    		data = verifyEntity.getReturnMess();
//    		JSONObject json = JSON.parseObject(data);
//    		if(VerifyUtil.hasToken(json.getString("userid"), json.getString("token"))){
//			
//				userInfo = gson.fromJson(data, UserInfo.class);
//				returnMess = campusService.queryJoinTeam(userInfo);
//    		}else{
//    			returnMess = ResponseUtil.buildErrorResponse(ResponseUtil.ERROR_REFUSED, "用户验证失败！", null);
//    		}
//    	}else{
//    		returnMess = verifyEntity.getReturnMess();
//    	}
//    	
//    	System.out.println("listauth;response:"+returnMess);
//    	return ServerSecurity.custom_encrypt(returnMess);

    	return null;
    }
    
    @RequestMapping(value="/loginhistory")
    @ResponseBody
    public String loginHistoryReport(HttpServletRequest request, HttpServletResponse response){
//    	UserInfo userInfo = null;
//    	String data = "";
//    	String returnMess = "";
//    	//获取对请求的验证
//    	VerifyEntity verifyEntity = VerifyUtil.verifyRequest(request, "loginhistory");
//    	if(verifyEntity.isUseful()){
//    		data = verifyEntity.getReturnMess();
//    		JSONObject json = JSON.parseObject(data);
//    		if(VerifyUtil.hasToken(json.getString("userid"), json.getString("token"))){
//			
//				userInfo = gson.fromJson(data, UserInfo.class);
//				returnMess = campusService.loginHistoryReport(userInfo);
//    		}else{
//    			returnMess = ResponseUtil.buildErrorResponse(ResponseUtil.ERROR_REFUSED, "用户验证失败！", null);
//    		}
//    	}else{
//    		returnMess = verifyEntity.getReturnMess();
//    	}
//    	System.out.println("loginhistory;response:"+returnMess);
//    	return ServerSecurity.custom_encrypt(returnMess);
    	return null;
    }
    @RequestMapping(value="/authuser")
    @ResponseBody
    public String authUserReport(HttpServletRequest request, HttpServletResponse response){
//    	UserInfo userInfo = null;
//    	String data = "";
//    	String returnMess = "";
//    	//获取对请求的验证
//    	VerifyEntity verifyEntity = VerifyUtil.verifyRequest(request, "authuser");
//    	if(verifyEntity.isUseful()){
//    		data = verifyEntity.getReturnMess();
//    		JSONObject json = JSON.parseObject(data);
//    		if(VerifyUtil.hasToken(json.getString("userid"), json.getString("token"))){
//				
//				returnMess = campusService.authUserInfo(json);
//    		}else{
//    			returnMess = ResponseUtil.buildErrorResponse(ResponseUtil.ERROR_REFUSED, "用户验证失败！", null);
//    		}
//    	}else{
//    		returnMess = verifyEntity.getReturnMess();
//    	}
//    	System.out.println("listuser;response:"+returnMess);
//    	return ServerSecurity.custom_encrypt(returnMess);

    	return null;
    }
    
    @RequestMapping(value="/killuser")
    @ResponseBody
    public String killUserReport(HttpServletRequest request, HttpServletResponse response){
//    	UserInfo userInfo = null;
//    	String data = "";
//    	String returnMess = "";
//    	//获取对请求的验证
//    	VerifyEntity verifyEntity = VerifyUtil.verifyRequest(request, "killuser");
//    	if(verifyEntity.isUseful()){
//    		data = verifyEntity.getReturnMess();
//    		JSONObject json = JSON.parseObject(data);
//    		if(VerifyUtil.hasToken(json.getString("userid"), json.getString("token"))){
//				
//    		    HashMap<String, String> params = new HashMap<String, String>();
//    		    params.put("userid2", json.getString("userid2"));
//				returnMess = campusService.killUserInfo(userInfo, params);		
//				
//    		}else{
//    			returnMess = ResponseUtil.buildErrorResponse(ResponseUtil.ERROR_REFUSED, "用户验证失败！", null);
//    		}
//    	}else{
//    		returnMess = verifyEntity.getReturnMess();
//    	}
//    	System.out.println("killuser;response:"+returnMess);
//    	return ServerSecurity.custom_encrypt(returnMess);
    	return null;
    } 
    @RequestMapping(value="/listuser")
    @ResponseBody
    public String listUserReport(HttpServletRequest request, HttpServletResponse response){
//    	UserInfo userInfo = null;
//    	String data = "";
//    	String returnMess = "";
//    	//获取对请求的验证
//    	VerifyEntity verifyEntity = VerifyUtil.verifyRequest(request, "listuser");
//    	if(verifyEntity.isUseful()){
//    		data = verifyEntity.getReturnMess();
//    		JSONObject json = JSON.parseObject(data);
//    		if(VerifyUtil.hasToken(json.getString("userid"), json.getString("token"))){
//			
//				userInfo = gson.fromJson(data, UserInfo.class);
//				returnMess = campusService.listUserInfo(userInfo);
//    		}else{
//    			returnMess = ResponseUtil.buildErrorResponse(ResponseUtil.ERROR_REFUSED, "用户验证失败！", null);
//    		}
//    	}else{
//    		returnMess = verifyEntity.getReturnMess();
//    	}
//    	System.out.println("listuser;response:"+returnMess);
//    	return ServerSecurity.custom_encrypt(returnMess);
    	return null;
    } 
    

    
}
