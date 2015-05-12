package com.shuiwen.campusys.util;

import com.alibaba.fastjson.JSONObject;

public class ResponseUtil {
    public static final int SUCCESS_CODE = 1;
    public static final int SYSTEM_ERROR_CODE = 0;
    public static final int PARAM_ERROR_CODE = 400;
    public static final int FORBIDDEN_CODE = 403;

    //错误码
    public static final int ERROR_UNAUTHORIZED_USER_CODE = 1;
    public static final int ERROR_UNAUTHORIZED_USIM_CODE = 2;
    public static final int ERROR_UNAUTHORIZED_ADMIN_CODE = 3;
    public static final int ERROR_REFUSED = 4;
    
    //异常码
    
    //权限码
    public static final int AUTHORITY_REFUSED = 0;
    public static final int AUTHORITY_ADMIN = 1;
    public static final int AUTHORITY_GUEST = 2;
    
    public static String buildSuccessResponse(String info, Object result) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", SUCCESS_CODE);
        jsonObject.put("info", info);
        
        if(result == null){
        	jsonObject.put("data", "");
        }else{
        	jsonObject.put("data", result);
        }
//        return ServerSecurity.custom_encrypt(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }
    
    public static String buildErrorResponse(int errorCode, String info, String result) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", errorCode);
        jsonObject.put("info", info);
        if(result == null){
        	jsonObject.put("data", "");
        }else{
        	jsonObject.put("data", result);
        }
//        return ServerSecurity.custom_encrypt(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }
    
    public static String buildSystemErrorResponse() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", ResponseUtil.SYSTEM_ERROR_CODE);
        jsonObject.put("info", "system error");
//        return ServerSecurity.custom_encrypt(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }
    
}
